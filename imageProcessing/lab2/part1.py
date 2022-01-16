import cv2
import numpy as np
from matplotlib import pyplot as plt, colors
from sklearn import cluster
from sklearn.cluster import estimate_bandwidth
from sklearn.metrics import silhouette_score


def create_scatter_plot(original_image, channel, labels):
    # Set up the plot
    fig = plt.figure()
    axis = fig.add_subplot(1, 1, 1, projection="3d")

    # Normalize
    pixel_colors = original_image.reshape((np.shape(original_image)[0] * np.shape(original_image)[1], 3))
    norm = colors.Normalize(vmin=-1., vmax=1.)
    norm.autoscale(pixel_colors)
    pixel_colors = norm(pixel_colors).tolist()

    # Build scatter plot
    axis.scatter(channel[0].flatten(), channel[1].flatten(), channel[2].flatten(), facecolors=pixel_colors, marker=".")
    axis.set_xlabel(labels[0])
    axis.set_ylabel(labels[1])
    axis.set_zlabel(labels[2])
    plt.show()

    return


def create_histogram(original_image):
    image_histogram = cv2.calcHist([original_image], [0], None, [256], [0, 256])
    image_histogram = image_histogram / sum(image_histogram)

    return image_histogram


def create_histogram_plot(histogram, labels):
    plt.plot(histogram[0], label=labels[0], color='red')
    plt.plot(histogram[1], label=labels[1], color='green')
    plt.plot(histogram[2], label=labels[2], color='blue')

    plt.legend(loc='best')
    plt.show()
    plt.close()

    return


def image_segmentation(image, image_labels):
    x, y, z = image.shape
    image_2d = image.reshape(x * y, z)

    kmeans_cluster = cluster.KMeans(n_clusters=5)
    kmeans_cluster.fit(image_2d)
    cluster_centers = kmeans_cluster.cluster_centers_
    cluster_labels = kmeans_cluster.labels_

    plt.imshow(((cluster_centers[cluster_labels].reshape(x, y, z)) * 255).astype(np.uint8))
    plt.show()

    print(f"\tSKlearn Silhouette Score: {silhouette_score(image_2d, cluster_labels, sample_size=100)}")

    bandwidth = estimate_bandwidth(image_2d, quantile=0.2, n_samples=500)
    mshift_cluster = cluster.MeanShift(bandwidth=bandwidth, bin_seeding=True)
    mshift_cluster.fit(image_2d)
    cluster_centers = mshift_cluster.cluster_centers_
    cluster_labels = mshift_cluster.labels_

    plt.imshow(((cluster_centers[cluster_labels].reshape(x, y, z)) * 255).astype(np.uint8))
    plt.show()

    print(f"\tSKlearn Silhouette Score: {silhouette_score(image_2d, cluster_labels, sample_size=100)}")
    return


# Read the image and convert to rgb
img = cv2.imread('me-ktm.jpg')
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

# Split the channels
r, g, b = cv2.split(img)
create_scatter_plot(img, [r, g, b], ['red', 'green', 'blue'])

# Convert rgb to hsv
hsv_img = cv2.cvtColor(img, cv2.COLOR_RGB2HSV)

# Split the channels
h, s, v = cv2.split(hsv_img)
create_scatter_plot(hsv_img, [h, s, v], ['hue', 'saturation', 'value'])

# Create histograms
red_histogram, green_histogram, blue_histogram = create_histogram(r), create_histogram(g), create_histogram(b)
# Plot histograms
create_histogram_plot([red_histogram, green_histogram, blue_histogram], ['red', 'green', 'blue'])

# Create histograms
hue_histogram, saturation_histogram, value_histogram = create_histogram(h), create_histogram(s), create_histogram(v)
# Plot histograms
create_histogram_plot([hue_histogram, saturation_histogram, value_histogram], ['hue', 'saturation', 'value'])

# Equalize the histograms
r_equalized, g_equalized, b_equalized = cv2.equalizeHist(r), cv2.equalizeHist(g), cv2.equalizeHist(b)
h_equalized, s_equalized, v_equalized = cv2.equalizeHist(h), cv2.equalizeHist(s), cv2.equalizeHist(v)

# RGB image segmentation
image_segmentation(img, ['red', 'green', 'blue'])

# HSV image segmentation
image_segmentation(hsv_img, ['hue', 'saturation', 'value'])

# RGB + HSV image segmentation
comb_img = np.concatenate((img, hsv_img), axis=2)
image_segmentation(comb_img, [])

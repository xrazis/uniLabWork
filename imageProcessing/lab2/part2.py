import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt
from skimage.util import random_noise


def compare_images(image_1, image_2):
    diff = image_1 - image_2
    return np.count_nonzero(abs(diff))


# Read the image and convert to b&w
img = cv.imread('me-ktm.jpg', 0)

# Detect edges with Canny
canny_edges_0 = cv.Canny(img, 100, 200)
plt.imshow(canny_edges_0, cmap='gray')
plt.show()

# Add Gaussian noise to image
gaussian_img = random_noise(img, mode='gaussian', seed=None, clip=True)
cv.imwrite('me-ktm-gaussian.jpg', gaussian_img)
cv.imshow('Image with Gaussian noise', gaussian_img)

# Clean with median filter
median_img = cv.medianBlur((255 * gaussian_img).astype(np.uint8), 5)
cv.imwrite('me-ktm-median.jpg', median_img)
cv.imshow('Gaussian noise image cleared with median filter', median_img)

# Wait for user keypress then destroy windows
cv.waitKey()
cv.destroyAllWindows()

# Detect edges with Canny
canny_edges_1 = cv.Canny(median_img, 100, 200)
plt.imshow(canny_edges_1, cmap='gray')
plt.show()

# Compare the two edge images
print('The two images differ by {} pixels'.format(compare_images(canny_edges_0, canny_edges_1)))

# Detect edges with Sobel
sobel_edges = cv.Sobel(gaussian_img, cv.CV_64F, 1, 1, ksize=7)
sobel_edges = np.uint8(np.absolute(sobel_edges))
plt.imshow(sobel_edges, cmap='gray')
plt.show()
print('The two images differ by {} pixels'.format(compare_images(canny_edges_0, sobel_edges)))

# Detect edges with Laplace
laplace_edges = cv.Laplacian(gaussian_img, cv.CV_64F, ksize=7)
laplace_edges = np.uint8(np.absolute(laplace_edges))
plt.imshow(laplace_edges, cmap='gray')
plt.show()
print('The two images differ by {} pixels'.format(compare_images(canny_edges_0, laplace_edges)))

import random

import cv2
import numpy as np
from skimage.metrics import structural_similarity as ssim


def salt_and_pepper(image, prob):
    noisy_image = np.zeros(image.shape, np.uint8)

    for i in range(image.shape[0]):
        for j in range(image.shape[1]):
            rand = random.random()
            if rand < prob:
                noisy_image[i][j] = 0
            elif rand > (1 - prob):
                noisy_image[i][j] = 255
            else:
                noisy_image[i][j] = image[i][j]

    return noisy_image


# https://github.com/scikit-image/scikit-image/blob/main/skimage/util/noise.py#L39-L226
def speckle(image):
    gauss = np.random.normal(0, 1, image.size)
    gauss = gauss.reshape(image.shape[0], image.shape[1]).astype('uint8')
    noisy = image + image * gauss

    return noisy


# the lower the mse value the more similar the two images are
def mean_square_error(original_image, filtered_image):
    mse = np.sum((original_image.astype("float") - filtered_image.astype("float")) ** 2)
    mse /= float(original_image.shape[0] * filtered_image.shape[1])

    return mse


print('\nWelcome!\n')

# read the image
img = cv2.imread('jinx.jpg', 0)

# add noise
sp_img = salt_and_pepper(img, 0.05)
cv2.imshow('Image with salt and pepper noise filter', sp_img)

# add noise
s_img = speckle(img)
cv2.imshow('Image with speckle noise filter', s_img)

cv2.waitKey()
cv2.destroyAllWindows()

print('Metric\t\tFunction\t\tScore')

# median filter
sp_median_img = cv2.medianBlur(sp_img, 5)
cv2.imshow('Salt and pepper noise removed with median filter', sp_median_img)
sp_median_ssim, _ = ssim(img, sp_median_img, full=True)
print('SSIM\t\tsp-median\t\t{:.3f}'.format(sp_median_ssim))
s_median_mse = mean_square_error(img, sp_median_img)
print('MSE\t\t\tsp-median\t\t{:.3f}'.format(s_median_mse))

# bilateral filter
sp_bilateral_img = cv2.bilateralFilter(sp_img, 9, 150, 150)
cv2.imshow('Salt and pepper noise removed with bilateral filter', sp_bilateral_img)
sp_bilateral_ssim, _ = ssim(img, sp_bilateral_img, full=True)
print('SSIM\t\tsp-bilateral\t{:.3f}'.format(sp_bilateral_ssim))
s_bilateral_mse = mean_square_error(img, sp_bilateral_img)
print('MSE\t\t\tsp-bilateral\t{:.3f}'.format(s_bilateral_mse))

# gaussian filter
sp_gaussian_img = cv2.GaussianBlur(sp_img, (5, 5), 2)
cv2.imshow('Salt and pepper noise removed with gaussian filter', sp_gaussian_img)
sp_gaussian_ssim, _ = ssim(img, sp_gaussian_img, full=True)
print('SSIM\t\tsp-gaussian\t\t{:.3f}'.format(sp_gaussian_ssim))
s_gaussian_mse = mean_square_error(img, sp_gaussian_img)
print('MSE\t\t\tsp-gaussian\t\t{:.3f}'.format(s_gaussian_mse))

# median filter
s_median_img = cv2.medianBlur(s_img, 3)
cv2.imshow('Speckle noise removed with median filter', s_median_img)
s_ssim_score, _ = ssim(img, s_median_img, full=True)
print('SSIM\t\ts-median\t\t{:.3f}'.format(s_ssim_score))
s_median_mse = mean_square_error(img, s_median_img)
print('MSE\t\t\ts-median\t\t{:.3f}'.format(s_median_mse))

# bilateral filter
s_bilateral_img = cv2.bilateralFilter(s_img, 9, 150, 150)
cv2.imshow('Speckle noise removed with bilateral filter', s_bilateral_img)
s_bilateral_ssim, _ = ssim(img, s_bilateral_img, full=True)
print('SSIM\t\ts-bilateral\t\t{:.3f}'.format(s_bilateral_ssim))
s_bilateral_mse = mean_square_error(img, s_bilateral_img)
print('MSE\t\t\ts-bilateral\t\t{:.3f}'.format(s_bilateral_mse))

# gaussian filter
s_gaussian_img = cv2.GaussianBlur(s_img, (5, 5), 2)
cv2.imshow('Speckle noise removed with gaussian filter', s_gaussian_img)
s_gaussian_ssim, _ = ssim(img, s_gaussian_img, full=True)
print('SSIM\t\ts-gaussian\t\t{:.3f}'.format(s_gaussian_ssim))
s_gaussian_mse = mean_square_error(img, s_gaussian_img)
print('MSE\t\t\ts-gaussian\t\t{:.3f}'.format(s_gaussian_mse))

cv2.waitKey()
cv2.destroyAllWindows()

print('\nBye!')

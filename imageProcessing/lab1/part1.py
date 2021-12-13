import cv2
import numpy as np


def solarize(original_image, thres_value):
    # get image dimensions
    height, width = original_image.shape
    # create blank solarized image
    solarized_img = np.zeros(original_image.shape, np.uint8)

    # loop over original_image
    for i in range(0, height):
        for j in range(0, width):
            # if the pixel value is over the threshold, solarize
            if original_image[i, j] > thres_value:
                solarized_img[i, j] = 255 - original_image[i, j]
            else:
                solarized_img[i, j] = original_image[i, j]

    return solarized_img


# read the image
img = cv2.imread('jinx.jpg', 0)

# solarize and save each image
gray_image_64 = solarize(img, 64)
cv2.imwrite("gray_image_64.jpg", gray_image_64)

gray_image_128 = solarize(img, 128)
cv2.imwrite("gray_image_128.jpg", gray_image_128)

gray_image_192 = solarize(img, 192)
cv2.imwrite("gray_image_192.jpg", gray_image_192)

# path image
import os, os.path
from PIL import Image

import numpy as np

path_file_img_movie_action = r"C:\Users\Marco\Documents\Projet\Root\Project\Dataset\DatasetAction"
path_file_img_movie_comedy = r"C:\Users\Marco\Documents\Projet\Root\Project\Dataset\DatasetComedy"
extension = [".png"]
images_action = []
images_actions_class = []

for element in os.listdir(path_file_img_movie_action):
    ext = os.path.splitext(element)[1]
    if ext.lower() not in extension:
        continue
    image = Image.open(os.path.join(path_file_img_movie_action,element))
    array = np.array(image) / 255
    images_action.append(array.flatten())
    #ToDo add another class to get 3 values 1,-1,-1
    images_actions_class.append(np.array([1,-1]))
print(images_action)
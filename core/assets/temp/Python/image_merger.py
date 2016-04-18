#CHRISTOPHER RODRIGUEZ
#Use this to stitch images together.
#It will stitch all png images together in a directory.
#CLEAN OUT THE RESULT (test.png) BEFORE RUNNING AGAIN!

from PIL import Image
import os

img_ls = []

for i in os.listdir():
    if(".png" in i):
        img_ls.append(Image.open(i))




image_size = img_ls[0].size[0]*len(img_ls),img_ls[0].size[1]

new_im = Image.new('RGBA', image_size)



xOff=0
for i in img_ls:
    new_im.paste(i,(xOff,0))
    xOff+=i.size[0]

new_im.save('test.png')

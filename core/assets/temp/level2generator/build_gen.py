from PIL import Image
import os
from random import randrange


directories = "ground/","wall/","floor/"
grounds = []
walls = []
floors = []
tile_size = 100,100;


img_ls =[grounds,walls,floors]

for j in range(0,len(directories)):
    for i in os.listdir(directories[j]):
        if os.path.isfile(os.path.join(directories[j],str(i))):
            img = Image.open(os.path.join(directories[j],str(i)))
            img = img.resize(tile_size,Image.ANTIALIAS)
            img_ls[j].append(img)
            
    
    
pattern = open("pattern.txt", "r")
lines = pattern.readlines()
yCount = len(lines)


image_size = tile_size[0]*(len(lines[0])-1),tile_size[1]*yCount

new_im = Image.new('RGBA', image_size)

collider_points = [[False]*(len(lines[0])-1) for _ in range(len(lines))]


xOff = 0
yOff = 0
collision_data = []
item_spawns = []
for y in range(0,len(lines)):
    for x in range(0,len(lines[y])):
        if((lines[y][x])=='0' or lines[y][x]=='1' or lines[y][x]=='2'or lines[y][x]=='3'):
            if(lines[y][x] == '3'):
                item_spawns.append([x,y])
                string_to_use = '2'
            else:
                string_to_use = lines[y][x]
            randnum = randrange(0,len(img_ls[int(string_to_use)]))
            new_im.paste(img_ls[int(string_to_use)][randnum],(xOff,yOff))
            if(lines[y][x] == '1'):
                collider_points[y][x] = True
            xOff+=tile_size[0]
    yOff+=tile_size[1]
    xOff=0



collision_data = []
for y in range(0,len(collider_points)):
    for x in range(0,len(collider_points[y])):
        if(collider_points[y][x]):
            if(y+1< len(collider_points) and collider_points[y+1][x]):
                big_y = y + 1
                y_counter = 2
                while(y+y_counter<len(collider_points) and collider_points[y+y_counter][x]):
                    big_y = y + y_counter
                    y_counter+=1
                x1 = round((x*tile_size[0])/image_size[0],5)
                y1 = round((image_size[1]-((big_y*tile_size[1])+tile_size[1]))/image_size[1],5)
                x2 = round((((x*tile_size[0])+tile_size[0])/image_size[0]) - x1,5)
                y2 = round(((image_size[1]-(y*tile_size[1]))/image_size[1]) - y1,5)
                mod_str = str(x1)+"f,"+str(y1)+"f,"+str(x2)+"f,"+str(y2)+"f"
                collision_data.append(mod_str)         
                for i in range(y,big_y+1):
                    collider_points[i][x]=False
                         

for y in range(0,len(collider_points)):
    for x in range(0,len(collider_points[y])):
        if(collider_points[y][x]):
            big_x = x
            x_counter = 1
            while(x+x_counter<len(collider_points[y]) and collider_points[y][x+x_counter]):
                big_x = x + x_counter
                x_counter+=1
            x1 = round((x*tile_size[0])/image_size[0],5)
            y1 = round((image_size[1]-((y*tile_size[1])+tile_size[1]))/image_size[1],5)
            x2 = round((((big_x*tile_size[0])+tile_size[0])/image_size[0]) - x1,5)
            y2 = round(((image_size[1]-(y*tile_size[1]))/image_size[1]) - y1,5)
            mod_str = str(x1)+"f,"+str(y1)+"f,"+str(x2)+"f,"+str(y2)+"f"
            collision_data.append(mod_str)         
            for i in range(x,big_x+1):
                collider_points[y][i]=False
                     



item_data = []            
for i in range(0,len(item_spawns)):
    x1 = round((item_spawns[i][0]*tile_size[0])/image_size[0],5)
    y1 = round((image_size[1]-((item_spawns[i][1]*tile_size[1])+tile_size[1]))/image_size[1],5)
    item_loc = str(x1)+"f:"+str(y1)+"f:item"
    item_data.append(item_loc);
    
f = open('collOutput.txt','w')
for i in collision_data:
    f.write(i+"\n")
f.write("END\n")
for i in item_data:
    f.write(i+"\n")
f.close()


                    


pattern.close()
new_im.save('test.png')

# -*- coding: utf-8 -*-
import cv2
import sys

CAM_ID = 0
camid = CAM_ID

# ?λ???¬μ©?λ λ§μ?λ§μ cv2.CAP_DSHOW μΆκ?
cam = cv2.VideoCapture(camid, cv2.CAP_DSHOW)

# λ¦¬λ???¬μ©?λ ?λ? κ°μ΄ ?¬μ©
# cam = cv2.VideoCapture(camid)
if cam.isOpened() == False:
    print ('cant open the cam (%d)' % camid)

ret, frame = cam.read()

if frame is None:
    print ('frame is not exist')

# pngλ‘??μΆ ?μ΄ ?μ ???imagePath = 'saveimage.png'
cv2.imwrite(imagePath,frame, params=[cv2.IMWRITE_PNG_COMPRESSION,0])
cam.release()

# Get user supplied values
cascPath = "haarcascade_frontalface_default.xml"

# Create the haar cascade
faceCascade = cv2.CascadeClassifier(cascPath)

# Read the image
image = cv2.imread(imagePath)
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# Detect faces in the image
faces = faceCascade.detectMultiScale(
    gray,
    scaleFactor=1.1,
    minNeighbors=5,
    minSize=(30, 30),
    flags=cv2.CASCADE_SCALE_IMAGE
)

print(len(faces))
import numpy as np
from datascience import *

return_x = []
return_y = []
return_rotation = []

for i in np.arange(20):
    x_value = '-'
    y_value = '-'
    z_value = '-'

    first_number = np.random.choice(np.arange(3))
    if first_number == 0:
        x_value = np.random.choice(np.arange(10))
    elif first_number == 1:
        y_value = np.random.choice(np.arange(10))
    else:
        z_value = np.random.choice(np.arange(-20, 30))
    
    def driveWithDirections(x, y, z):
        return_x.append(x)
        return_y.append(y)
        return_rotation.append(z)
    driveWithDirections(x_value, y_value, z_value)

what_changed = []
def whatIsChanged(x, y, z):
    for i in np.arange(len(x)):
        if x[i] != '-':
            what_changed.append('x_position')
        elif y[i] != '-':
            what_changed.append('y_position')
        elif z[i] != '-':
            what_changed.append('rotation')
        else:
            what_changed.append('nothing, code is stupid')

whatIsChanged(return_x, return_y, return_rotation)
# rainy-hills

### Description
Given an array of integers representing the surface of a hill.
Calculate the expected total volume of water in units that will be collected over the 
entire surface of the hill after a rainfall. 


#### Solution
A web application to expose the calculation as a REST resource.Tested with apache-tomee-plume-7.0.4.

_rainy-hills/collectedrainwater/{surface}_

_surface_ path param is a comma separated string of integers representing the surface points of a hill.
#### Example
###### Request
_http://localhost:8080/rainy-hills/collectedrainwater/1,5,2,3,1,7,2,4_
###### Response
_{"hill":{"surface":[1,5,2,3,1,7,2,4]},"collectedRainWaterVolume":11}_


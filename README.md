nearybyChallenge
================

Big O Analysis
After exploring other approaches I have come to realize that this is not the best approach to solving this problem but since the other answer already exist on using Quad Trees there is no need for me to redo this work. Instead what I will explain is why and where it is better.

If the question were simply "Find the k closest topics for the given coordinates" my approach would do the following:
1. Insert all the topics t in with t operations (since I don't use a tree it is not tlog(t))
2. Calculate all the distances. This takes t operations
3. Next I look for the kth furthest topic. From the CLRS if the medians of medians is picked every time the kth smallest can be found in O(t) operations.
4. Get all the topics less than kth furthest topic. This takes t operations.
5. Sort the k topics klog(k)

In total this would mean it takes me t(insert) + t(calculate distances)  + t(find kth smallest distance) + t(find smallest to kth smallest distances) + klog(k)= 4t +klog(k) to find the k elements to the provided coordinates.

The Quad Tree approach ( I recommend reading the original paper: http://www.researchgate.net/publication/220197855_Quad_Trees_A_Data_Structure_for_Retrieval_on_Composite_Keys/links/0c9605273bba2ece7b000000)
1. Insert all the topics tlog(t)
2. Perform collision detection and retrieve the point that collide with the given coordinates as its center and create an object with an expanding radius so that you collect more topics if k is greater than the number of the intitial topics that you retrieve with your collision object. This takes log(t) for each time you have to do this collision detection
3. Sort the topics by distance from the given coordinates ~ klog(k)

In total this takes tlog(t) + xlog(t) + klog(k) where x is the number to time you have to do step 2.

If only one query had to be done my approach would win since 3t + klog(k) < tlog(t) + xlog(t) + klog(k)

HOWEVER

The question has an additional component where the number of queries is a variable and all the topic data stays the same. Since this is the case I am force to calculate distances q times resulting in the following:
t (insert) + qt (calculate distances) + t(find kth smallest distance) + t(find smallest to kth smallest distances) + qklog(k) = qt + 3t + qklog(k)

Where as the other approach has the following
tlog(t) + qxlog(t) + qklog(k)

In this case: tlog(t) + qxlog(t) + qklog(k)<  qt + 3t + qklog(k) if q = t then
tlog(t) + xtlog(k) + tklog(t) < t^2 + 3t + tklog(t)
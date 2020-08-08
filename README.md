# 594GroupProject
594 Group Project Report for Murdoc Khaleghi and Cayde Roothoff


For our additional feature, we thought it would be interesting to examine if there was a relationship between home market values and fines.  Specifically, we examined the Total Market Value to Total Fine Ratio, and then distributed that over the population by taking this ratio and dividing per capita.  We thought this would be interesting to examine because areas with greater market value might have higher fines because they have greater disposable income or the residents require greater parking enforcement.  We also recognized the opposite possibility, that parking fines are targeted in less affluent areas.  

For our project, we used four data structures based on when we thought it would optimization.  Those four structures were:
ArrayList
HashMap
TreeMap
HashMap of ArrayLists

	These are the situations we chose to use each of those structures.  

	ArrayList - We used ArrayLists when reading the properties file, capturing either the market value or livable area when the zip code matches the inputted zip code.  We chose an ArrayList because these did not need to be stored in a certain order and there are unknown number of how many match the zip code.  As we did not need to retrieve specific market values or livable areas, we simply can find the total by adding them in no particular order, and we can use the size method to find the number of entries for calculating the average.  

	HashMap - We used HashMaps when memoizing.  In every case, we would map a particular zip code to any relevant metric associated to it.  This allows retrieving a certain zip code value in O(1), versus having to go through a list trying to find the relevant zip code / value.  

	TreeMap - We used a TreeMap for mapping the zip codes to the total fines associated with those zip codes.  We used this for the ordering feature of TreeMaps, knowing for Q2 we would need to report these zip codes in order.  While a TreeMap requires greater upfront work to perform the ordering (O(log n) rather than O(1)), this work is performed to order the zip codes and then allow for easy reporting of them in order when queried.  

	HashMap of ArrayLists - This may not be considered a distinct data set to the others, but as this was a unique combination we used in specific cases worth mentioning.  As mentioned previously, we used ArrayLists to store certain values when reading the properties file.  To then ensure those lists of values were associated with the proper zip code, we used a HashMap, mapping the zip codes to the corresponding ArrayLists.  







# 594GroupProject
594 Group Project Report for Murdoc Khaleghi and Cayde Roothoff


For our additional feature, we thought it would be interesting to examine if there was a relationship between home market values and fines.  Specifically, we examined the Total Market Value to Total Fine Ratio, and then distributed that over the population by taking this ratio and dividing per capita.  We thought this would be interesting to examine because areas with greater market value might have higher fines because they have greater disposable income or the residents require greater parking enforcement.  We also recognized the opposite possibility, that parking fines are targeted in less affluent areas.  

For our project, we used three data structures based on when we thought it would optimize our functions.  Those three structures were:
LinkdList
HashMap
ZipCode object

	These are the situations we chose to use each of those structures.  

	LinkedList - We used LinkedLists when reading the properties file, capturing either the market value or livable area when the zip code matches the inputted zip code.  We chose a LinkedList because these did not need to be stored in a certain order and there are unknown number of how many match the zip code.  As we did not need to retrieve specific market values or livable areas, we simply can find the total by adding them in no particular order.  

	HashMap - We used HashMaps when memoizing.  In every case, we would map a particular zip code object to any relevant metric associated to it.  This allows retrieving a certain zip code value in O(1), versus having to go through a list trying to find the relevant zip code / value.  

	ZipCode object - We used a ZipCode object when memoizing any information related to a specific zipcode. These were then stored in a hasmhap as stated above.







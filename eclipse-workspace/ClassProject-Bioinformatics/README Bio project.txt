******************************************************************************
* Project 4 Bioinformatics
* README
* A+ Coders
* Jake Andrews & Devan Craig
* May 1, 2019
******************************************************************************
Description:
This program uses two main classes; one to create a BTree and another to search the tree. In order to use these classes it must use a TreeObject, BTreeNode, Parser and BTree. The TreeObject class holds the keys for the BTree. These keys are created by the file passed in and from that we can create our BTree. The BTree takes in the keys which are inserted into the tree and later can be searched and deleted from the BTree class. Making our file Parser is important in making the correct subsequence. All of these classes are used to create our Gene Bank Btree which gives a dump file that has our key and the frequency. The Gene Bank Btree also gives us a .data file which is used for our GeneBankSearch class. Running the .data file in the Search class with the query file gives us specific DNA strings found by the query file that we can search for in our .data file. 


Compiling:
javac GeneBankCreateBTree.java
java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequencelength>  [<debug level>]


Degree - 15 is the optimal degree anything besides that will give you a slightly different frequency
Cache 0 - will be implemented with no cache
Cache 1 - will automatically be put back to 0 since there is no cache implemented
Debug 0 - will make a .data file without the making the dump file 
Debug 1 - will make the .data file with a dump file 


javac GeneBankSearch
java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<debug-level must be 0>]


Cache 0 - will be implemented with no cache
Cache 1 - will automatically be put back to 0 since there is no cache implemented
Query file - must have have the same frequency as the btree file
Debug 0 - grabs a DNA strings of a specific sequence length that we want to search for in the specified btree file


GeneBankCreateBTree                                                                                           debug = 0
******************************************************************************


Cache
	Degree
	Gbk file
	Sequence Length
	Time(sec)
	0
	3
	test3.gbk
	7
	5.78
	0
	15
	test3.gbk
	7
	15.54
	0
	30
	test3.gbk
	7
	28.57
	



GeneBankCreateBTree                                                                                           debug = 1
******************************************************************************


Cache
	Degree
	Gbk file
	Sequence Length
	Time(sec)
	0
	3
	test3.gbk
	7
	6.29
	0
	15
	test3.gbk
	7
	14.63
	0
	30
	test3.gbk
	7
	27.01
	



GeneBankSearch                                                                                                    debug = 0
******************************************************************************


Cache
	BTree file
	Query file
	Time(sec)
	0
	test3.gbk.btree.data.7.3
	7
	8.46
	0
	test3.gbk.btree.data.7.15
	7
	21.10
	0
	test3.gbk.btree.data.7.30
	7
	40.13
Approach and Implementation

Mapper Logic:
The Mapper class reads each line of input, splits it into words, and emits each word along with a count of 1.
Special characters are removed, and words are converted to lowercase to avoid case-sensitive duplicates.

Example Output from Mapper:

hello 1
world 1
hello 1

Reducer Logic:
The Reducer class takes the word-count pairs emitted by the Mapper and aggregates the counts for each unique word.
It emits the final count for each word.

Example Output from Reducer:

hello 2
world 1

Execution Steps

Start Hadoop Cluster: docker compose up -d

Build the Project Using Maven: mvn install

Move JAR File to Shared Folder: mv target/*.jar shared-folder/input/data/

Copy JAR to Docker Container: docker cp shared-folder/input/data/WordCountUsingHadoop-0.0.1-SNAPSHOT.jar resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/

Copy Dataset to Container: docker cp shared-folder/input/data/input.txt resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/

Access ResourceManager Container: docker exec -it resourcemanager /bin/bash
cd /opt/hadoop-3.2.1/share/hadoop/mapreduce/

Setup HDFS Directories:

hadoop fs -mkdir -p /input/dataset
hadoop fs -put ./input.txt /input/dataset

Run the MapReduce Job:

hadoop jar WordCountUsingHadoop-0.0.1-SNAPSHOT..jar com.example.controller.Controller /input/dataset/input.txt /output

View Output:

hadoop fs -cat /output/*

Copy Output to Local Machine:

hdfs dfs -get /output /opt/hadoop-3.2.1/share/hadoop/mapreduce/
docker cp resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/output/ shared-folder/output/

Challenges Faced & Solutions

Hadoop Configuration Issues:

Challenge: Errors due to incorrect file paths or environment settings.

Solution: Verified Hadoop configurations and paths before running commands.

Dataset Copy Errors:

Challenge: Difficulty in transferring files between local machine and container.

Solution: Used Docker cp command carefully to manage file transfers.

Case Sensitivity and Special Characters:

Challenge: Inconsistent word counts due to special characters and case differences.

Solution: Normalized input by converting words to lowercase and removing non-alphanumeric characters in the Mapper.

Sample Input and Output

Input File (input.txt)

Hadoop is a framework that allows for the distributed processing of large data sets across clusters of computers.
Hadoop is designed to scale up from single servers to thousands of machines, each offering local computation and storage.
Rather than rely on hardware to deliver high-availability, the library itself is designed to detect and handle failures at the application layer.
MapReduce is a core component of the Hadoop ecosystems.

Expected Output:

a	2
across	1
allows	1
and	2
application	1
at	1
clusters	1
component	1
computation	1
computers	1
core	1
data	1
deliver	1
designed	2
detect	1
distributed	1
each	1
ecosystems	1
failures	1
for	1
framework	1
from	1
hadoop	3
handle	1
hardware	1
highavailability	1
is	4
itself	1
large	1
layer	1
library	1
local	1
machines	1
mapreduce	1
of	4
offering	1
on	1
processing	1
rather	1
rely	1
scale	1
servers	1
sets	1
single	1
storage	1
than	1
that	1
the	4
thousands	1
to	4
up	1

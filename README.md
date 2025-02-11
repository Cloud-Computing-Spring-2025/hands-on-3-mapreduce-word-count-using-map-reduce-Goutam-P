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

Start Hadoop Cluster:

docker compose up -d

Build the Project Using Maven:

mvn install

Move JAR File to Shared Folder:

mv target/*.jar shared-folder/input/data/

Copy JAR to Docker Container:

docker cp shared-folder/input/data/WordCountUsingHadoop-0.0.1-SNAPSHOT.jar resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/

Copy Dataset to Container:

docker cp shared-folder/input/data/input.txt resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/

Access ResourceManager Container:

docker exec -it resourcemanager /bin/bash
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

Hello Hadoop
Hello World

Expected Output:

hadoop 1
hello 2
world 1

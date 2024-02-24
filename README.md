# MaerskDemo
Steps to test the application
1. Clone the project using command " git clone https://github.com/vs93/MaerskDemo.git"
2. Navigate inside the "MaerskDemo" Folder and fire the command from terminal "docker-compose up -d", which will bring up the docker container for mongo db
3. Open the project in Intellij with pom.xml
4. Run the main class "MaerskDemoApplication"
5. Download the post main collection file from the repo itself in the directory "PostmanCollection"


Note: Incase if someone is facing issues while running project in Intellij due to intial overhead of import complexities there is a simple way also:
1. Follothe above mentioned steps till step 2. Post that navigate to the target direct from your terminal and fire the following command
   " java -jar Demo-0.0.1-SNAPSHOT.jar "
2. Once its up import post man collection and run it

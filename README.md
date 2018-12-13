# easydfs
   Easydfs is based on minio as a file storage. It is just a layer of web application encapsulated on the MinioClient as a file server, and it (minio) supports distributed, distributed configuration can be viewed here: https://docs.minio.io

# require
   JDK8   
   Minio（https://github.com/minio/minio）  


# quickstart
    1. git clone easydfs  
    2. mvn clean install -Dmaven.test.skip=true
    3. execute script/easydfs-start.bat （This script is: Windows platform, Linux platform itself research）
    4. Open postman visit http://127.0.0.1:8001/easydfs/file/upload
      4.1.1 It has 2 parameters: 1. bucket folder name 2. file file. Upload successfully returned: background custom file name
    5. Download interface: http://127.0.0.1:8001/easydfs/file/download/bucketName/fileName


# About distributed MiniioServer configuration (Windows platform)
    1. download minio.exe (https://dl.minio.io/server/minio/release/windows-amd64/minio.exe)
    2. need at least 4 nodes
    3. each service node needs to open 9000 ports
    4. execute the following script in turn at each service node
      4.1. set MINIO_ACCESS_KEY=<ACCESS_KEY>
      4.2. set MINIO_SECRET_KEY=<SECRET_KEY>
      4.3. minio.exe server http://192.168.0.1/D:/minio/file http://192.168.0.2/D:/minio/file http://192.168.0.3/D:/minio/file http://192.168.0.4/D:/minio/file

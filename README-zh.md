# easydfs
   easydfs是基于minio作为文件存储，它只是在MinioClient上封装的一层Web应用， 可以作为文件服务器， 并且它(minio)支持分布式 ， 分布式配置可以查看这里：https://docs.minio.io

# 必需
   JDK8   
   Minio  


# 快速开始
    1. git clone easydfs  
    2. mvn clean install -Dmaven.test.skip=true
    3. 执行script/easydfs-start.bat （这个脚本为：Windows平台， Linux平台自己研究）


# 关于分布式MinioServer配置（Windows平台）
    1. 下载minio.exe (https://dl.minio.io/server/minio/release/windows-amd64/minio.exe)
	  2.至少需要4个节点
	  3.每个服务节点需要开放9000端口
	  4.在每个服务节点执行以下脚本
		  4.1 set MINIO_ACCESS_KEY=<ACCESS_KEY>
		  4.2 set MINIO_SECRET_KEY=<SECRET_KEY>
		  4.3 minio.exe server http://192.168.0.1/D:/minio/file http://192.168.0.2/D:/minio/file http://192.168.0.3/D:/minio/file http://192.168.0.4/D:/minio/file

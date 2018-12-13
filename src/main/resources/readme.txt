


set MINIO_ACCESS_KEY=BJ1XTH099QIICK06Z6ZA
set MINIO_SECRET_KEY=8wzLEflTWAnfirMk7WxmCHLuNXfZiFj+eb4OSt6h


minio.exe server http://172.16.0.122/D:/minio/file1 http://172.16.0.122/D:/minio/file2 http://172.16.0.68/D:/minio/file http://172.16.0.68/D:/minio/file2

minio.exe server http://172.16.0.140/D:/minio/file http://172.16.0.140/D:/minio/file2 http://172.16.0.68/D:/minio/file http://172.16.0.68/D:/minio/file2


1.至少需要4个节点

2.需要暴露9000端口

3.需要依次执行上面脚本命令
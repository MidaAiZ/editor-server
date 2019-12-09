#!/bin/bash
rm -rf ./target
echo "正在打包..."
mvn clean
mvn package -Pprod
file=`ls ./target | grep jar$`
if [ $file -z ]
then
   echo "找不到目标文件"
   exit
else
   echo "上传目标文件${file}"
fi
mv ./target/${file} ./target/tabplus-production.jar
sftp root@139.196.120.93 <<EOF
put ./target/tabplus-production.jar tabplus-production.jar
EOF
ssh root@139.196.120.93 "./run-tabplus_prod.sh"

echo "程序执行完毕"

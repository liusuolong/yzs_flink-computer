./bin/flink run -d \
-m yarn-cluster \
-yn 2 \
-yjm 1024 \
-ytm 1024 \
-ynm stateful-computation  \
-c com.yzs.data.main.driverPostingMain  \
/root/flink/data-middle-1.0-SNAPSHOT.jar

# 启动程序命令
./flink run    -d \
 -p 1 \
-c com.yzs.data.main.driverPostingMain  \
/root/flink/data-middle-1.0-SNAPSHOT.jar

#任务槽设置
--yarnslots 3


#查询列表
./flink list --all

#杀死命令
flink cancel 471ede242bd2fa22d32ed147a025e5d2




从checkPoint启动
 ./bin/flink run -d \
 -m yarn-cluster \
 -yn 2 -yjm 1024 \
 -ytm 1024 \
 -ynm state_test33  \
 -s  \
 -c com.yzs.data.main.driverPostingMain \
 /zywa/tmp_test/flink-1.7.2/flink3.jar \
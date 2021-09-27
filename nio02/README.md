串行GC:UseSerialGC  16G


java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.hyc.GCLogAnalysis
Youg GC :30-36-0ms(一直Full gc),Full gc:30-60ms

调整-Xms1g -Xmx1g
生成对象：106104
Youg GC：10-70ms m Full GC: 60-70ms

eden space 279616K,   4% used
tenured generation   total 699072K, used 406709K 58% used

调整-Xms512m -Xmx1g
2次Full GC 第一次60ms  第二次90ms
eden space 228416K,  96% used    96% used
tenured generation   total 570944K, used 342565K  59% used

创建对象数量在 1.4w
调整-Xms4g -Xmx4g

发生2次Full GC  延迟：100ms

串行GC：xms,xmx配置较小时，频繁触发Full GC,GC后老年代内存使用量占比比较大，
增大xms,xmx配置时，增大内存，FULL CG时暂停时长为100ms


并行：UseParallelGC

运行1秒测试：
调整-Xms512m -Xmx512m

FULL GC 7次  暂停时长：30-40--50-60ms
ParOldGen  total 349696K, used 327064K  93% used   GC后内存使用量比较高  
增大内存：大量FULL GC，老年代使用率99%,暂停时间40-50ms

调整-Xms1g -Xmx1g
ParOldGen       total 699392K, used 361733K   51% used
[Times: user=0.19 sys=0.00, real=0.05 secs]  暂停时间190ms，通过并行变为50ms

调整-Xms4g -Xmx4g ，增大内存
1次FULL GC
[ParOldGen: 2692603K->376233K(2796544K)]  GC之后老年代使用率13%


CMS GC:

-Xms1g -Xmx1g
生成对象次数:1.2W, young gc时间：10-50ms

-Xms2g -Xmx2g
生成对象次数:1.3W, young gc时间：20-80ms

-Xms4g -Xmx4g
生成对象次数97564,young gc时间：20-100ms


G1 GC

-Xms1g -Xmx1g
生成对象次数40295,GC Workers: 4
young gc时间：0.3-0.9ms FULL GC: 0-4ms

-Xms1g -Xmx1g
生成对象次数95209,GC Workers: 4
young gc时间：6-16ms FULL GC: 10-20ms


-Xms2g -Xmx2g
生成对象次数95209,GC Workers: 4
young gc时间：7-27ms FULL GC: 10-30ms

-Xms4g -Xmx4g
生成对象次数140116,GC Workers: 4
young gc时间：20-37ms FULL GC: 20-40ms
GC次数：38

-Xms8g -Xmx8g
生成对象次数98364,GC Workers: 4
young gc时间：26-29ms FULL GC: 30ms
GC次数：22



总结：
1.相同JVM参数下，内存基本一致时，串行时间是并行的两倍
2.JVM配置参数大于1g时，G1回收时间断，内存比较大时，效果更明显
3.JVM参数低于512时，full gc次数会频繁触发，创建对象比较少
4.cms gc回收时间波动大
#! /bin/bash

source /opt/sumscope/.ssinfo

typeset -l envinfo
if [ ! -n "$1" ]; then
    envinfo=${env_info}
    if [ ! -n "$envinfo" ]; then
         envinfo="default"
    fi
else
     envinfo=$1
fi
echo "envinfo:"${envinfo}

typeset -l initRedis
initRedis=$2
if [  "$initRedis" != "true" ]; then
    initRedis=false
fi
echo "initRedis:"${initRedis}

typeset -l includingAllData
includingAllData=$3
if [  "$includingAllData" != "true" ]; then
    includingAllData=false
fi
echo "includingAllData:"${includingAllData}

PROGRAM_NAME=qpwb-bdc-svc
PROGRAM_NAME_FULL=${PROGRAM_NAME}.jar
PROPERTIES_PATH=../conf/
SHELL_DIR=$(cd "$(dirname "$0")"; pwd)
source ${SHELL_DIR}/jvm.param
var1=${envinfo}_jvm_option_Xms_size
var2=${envinfo}_jvm_option_Xmx_size
var3=${envinfo}_jvm_option_Xmn_size
var4=${envinfo}_jvm_option_Xss_size
var5=${envinfo}_jvm_option_XXMM_size
JVM_OPTS_MS_SIZE=`eval echo '$'$var1`
JVM_OPTS_MX_SIZE=`eval echo '$'$var2`
JVM_OPTS_MN_SIZE=`eval echo '$'$var3`
JVM_OPTS_SS_SIZE=`eval echo '$'$var4`
JVM_OPTS_MM_SIZE=`eval echo '$'$var5`
if [ ! -n "${JVM_OPTS_MS_SIZE}" ]; then
    JVM_OPTS_MS_SIZE=4096m
fi
if [ ! -n "${JVM_OPTS_MX_SIZE}" ]; then
    JVM_OPTS_MX_SIZE=4096m
fi
if [ ! -n "${JVM_OPTS_MN_SIZE}" ]; then
    JVM_OPTS_MN_SIZE=2048m
fi
if [ ! -n "${JVM_OPTS_SS_SIZE}" ]; then
    JVM_OPTS_SS_SIZE=1024k
fi
if [ ! -n "${JVM_OPTS_MM_SIZE}" ]; then
    JVM_OPTS_MM_SIZE=512m
fi
PROGRAM_PATH=$(cd "$(dirname "${SHELL_DIR}/../lib/${PROGRAM_NAME_FULL}")"; pwd)
JAVA_OPTS="-server -Xms${JVM_OPTS_MS_SIZE} -Xmx${JVM_OPTS_MX_SIZE} -Xmn${JVM_OPTS_MN_SIZE} -Xss${JVM_OPTS_SS_SIZE} -XX:MaxMetaspaceSize=${JVM_OPTS_MM_SIZE} -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark -verbose:GC -XX:+PrintFlagsFinal"
PROGRAM_PID=`ps -ef | grep ${PROGRAM_NAME_FULL} | grep -v grep | awk '{print $2}' | wc -l`
if [ ${PROGRAM_PID} -gt 0 ]; then
	echo "service ${PROGRAM_NAME} already running."
	exit
fi
echo ""
echo "SHELL   PATH : $SHELL_DIR"
echo "SHELL   NAME : $0"
echo "PROGRAM NAME : ${PROGRAM_PATH}/${PROGRAM_NAME_FULL}"
echo "JAVA_OPTS    : $JAVA_OPTS"
echo "JRE          : $(which java)"
echo ""
echo "start service ..."
cd ${PROGRAM_PATH}
if [ -f ../nohup.out ]; then
	rm -fv ../nohup.out
fi
if [ ! -d ../log ]; then
	mkdir ../log
fi
nohup java ${JAVA_OPTS} -jar ${PROGRAM_PATH}/${PROGRAM_NAME_FULL} --spring.config.location=${PROPERTIES_PATH} --spring.profiles.active=${envinfo} --initRedis=$initRedis --includingAllData=$includingAllData > ../nohup.out 2>&1 &

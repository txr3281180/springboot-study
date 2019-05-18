#!/bin/bash
CURRENT_DATE=$(date +%Y-%m-%d)
PROGRAM_NAME=qpwb-bdc-svc
PROGRAM_NAME_FULL=${PROGRAM_NAME}.jar
SHELL_DIR=$(cd "$(dirname "$0")"; pwd)
PROGRAM_PATH=$(cd "$(dirname "${SHELL_DIR}/../lib/${PROGRAM_NAME_FULL}")"; pwd)

RET_CODE=0
PROGRAM_PID=`ps -ef | grep ${PROGRAM_NAME_FULL} | grep -v grep | awk '{print $2}'`
if [ ${PROGRAM_PID}x != ""x ]; then
	while [ $(grep "Started" ${PROGRAM_PATH}/../nohup.out|wc -l) -eq 0 ]; do
		sleep 5;
	done;
	echo "${PROGRAM_NAME} service is running"
else
	echo "${PROGRAM_NAME} service is not running"
	RET_CODE=1
fi
if [ -d ${PROGRAM_PATH}/../log ] && [ $(grep -E "ERROR" ${PROGRAM_PATH}/../nohup.out | wc -l)x != "0"x ]; then
	echo "service ${PROGRAM_NAME}: error message was found from nohup.out, please check!!!"
fi
exit ${RET_CODE}

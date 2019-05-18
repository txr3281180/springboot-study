#! /bin/bash

PROGRAM_NAME=qpwb-bdc-svc
PROGRAM_NAME_FULL=${PROGRAM_NAME}.jar

PROGRAM_PID=`ps -ef | grep ${PROGRAM_NAME_FULL}| grep -v grep | awk '{print $2}'` 

if [ ${PROGRAM_PID}x != ""x ]; then
	echo "stop ${PROGRAM_NAME} service at PID[${PROGRAM_PID}] ..."
	kill -9 ${PROGRAM_PID}
	if [ $? -eq 0 ]; then
		echo "stop ${PROGRAM_NAME} service at PID[${PROGRAM_PID}] success"
	else 
		echo "stop ${PROGRAM_NAME} service at PID[${PROGRAM_PID}] error"
	fi
else 
	echo "${PROGRAM_NAME} service is not running."
fi


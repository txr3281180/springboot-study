#!/bin/bash

PROCESSNUM=`ps -ef | grep com.sumscope.ngpc.creditbond.CreditbondApplication | grep -v grep | wc -l`

if [ $PROCESSNUM -gt 0 ]
    then
        kill -s 15 `ps -ef | grep com.sumscope.ngpc.creditbond.CreditbondApplication | grep -v grep | awk '{print $2}'`
        echo "ngpc-creditbond is running, stop success"
    else
    echo "ngpc-creditbond is not running, stop failed"
fi
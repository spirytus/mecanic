#!/bin/zsh

USER=panda
HOST=192.168.2.209
ARTIFACT=../out/artifacts/Mecanic_jar/Mecanic.jar
RUN_SCRIPT=roborun.sh

scp $ARTIFACT $USER@$HOST:
scp $RUN_SCRIPT $USER@$HOST:
ssh $USER@$HOST

. RUN_SCRIPT

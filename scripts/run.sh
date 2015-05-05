#!/bin/zsh

USER=panda
HOST=192.168.2.209
ARTIFACT=../out/artifacts/Mecanic_jar/Mecanic.jar
RUN_SCRIPT=roborun.sh

scp $ARTIFACT $USER@$HOST:
scp -C $RUN_SCRIPT $USER@$HOST:

ssh $USER@$HOST

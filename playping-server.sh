#!/bin/bash

while :; do
  target/universal/stage/bin/playping -Dpidfile.path=/tmp/playping.pid
done

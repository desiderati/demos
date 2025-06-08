#!/bin/bash
#
# Copyright (c) 2025 - Felipe Desiderati
#
# Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
# associated documentation files (the "Software"), to deal in the Software without restriction,
# including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
# and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
# subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all copies or substantial
# portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
# LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
# IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
# WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
# SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#

set -e

if ! id -u java; then
  USER_ID=${LOCAL_USER_ID:-1000}
  echo "Adding 'java' user..."
  useradd -m -d /home/java -u ${USER_ID} -s /bin/bash java
  chown -R ${USER_ID}:${USER_ID} /opt
  echo "User 'java' added with success!"
fi

if [[ ${ENABLE_DEBUG} = true ]]; then
  echo "Enabling Debug Mode on Port: 8090"
  JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8090"
fi

export JAVA_OPTS="$JAVA_OPTS -Xms$JAVA_XMS -Xmx$JAVA_XMX -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m
-Djava.net.preferIPv4Stack=true -XX:ParallelGCThreads=$JAVA_CPUS -XX:ConcGCThreads=$JAVA_CPUS
-Djava.util.concurrent.ForkJoinPool.common.parallelism=$JAVA_CPUS"

echo "Running Java App with following Options: $JAVA_OPTS"

exec gosu java "$@"

#!/bin/bash
gradle build >> /dev/null && java -jar build/libs/ebnf-calculator*

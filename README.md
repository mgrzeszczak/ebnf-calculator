# ebnf-calculator
[![Build Status](https://travis-ci.org/mgrzeszczak/ebnf-calculator.png)](https://travis-ci.org/mgrzeszczak/ebnf-calculator)
Calculator based on an unambiguous context-free grammar and using ebnf top-down parser.

# Grammar
```
expression -> expression add_op term | term
term -> term mul_op factor | factor
factor -> exponent ^ factor | exponent
exponent -> # exponent | root
root -> number | (expression)
mul_op -> * | /
add_op -> + | -
```

# Usage
```
./run.sh
>> 2+2*2
6.0
>> 10+10*5^2-(2*3*#4)
248.0
```

# License (MIT)
```
Copyright (c) 2017 Maciej Grzeszczak

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

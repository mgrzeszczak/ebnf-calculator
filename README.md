# ebnf-calculator
Calculator based on an unambiguous context-free grammar and using ebnf top-down parser.

## Grammar

```
expression -> expression add_op term | term
term -> term mul_op factor | factor
factor -> exponent ^ factor | exponent
exponent -> # exponent | root
root -> number | (expression)
mul_op -> * | /
add_op -> + | -
```

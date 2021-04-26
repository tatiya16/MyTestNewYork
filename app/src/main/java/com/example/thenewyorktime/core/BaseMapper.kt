package com.example.thenewyorktime.core

interface BaseMapper<in I, out O> {
    fun map(input: I?): O
}
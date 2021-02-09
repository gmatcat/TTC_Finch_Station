package com.ttc.finch_station.data.common.mapper.core

interface Mapper<I, O> {
    fun map(input: I): O
}
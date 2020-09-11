package org.acme.kogito.model

data class Person(val name: String, val age: Int, var isAdult: Boolean = false)

data class PersonEnvelope(var person: Person)
package com.ejercicio.cerradura.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cerradura")
class CerraduraController {

    @PutMapping("/estrella/{n}")
    fun cerraduraEstrella(@PathVariable n: Int): Set<String> {
        return calcularCerradura(n, true)
    }

    @PutMapping("/positiva/{n}")
    fun cerraduraPositiva(@PathVariable n: Int): Set<String> {
        return calcularCerradura(n, false)
    }

    private fun calcularCerradura(n: Int, esEstrella: Boolean): Set<String> {
        val result = mutableSetOf<String>()
        result.add("") // Agregamos la cadena vacía (λ)

        for (i in 1..n) {
            val currentSet = generarCadenasBinarias(i)
            result.addAll(currentSet)
        }

        if (!esEstrella) {
            result.remove("") // Eliminamos la cadena vacía si no es cerradura de Kleene
        }

        return result
    }

    private fun generarCadenasBinarias(longitud: Int): Set<String> {
        val result = mutableSetOf<String>()
        val maxNum = 1 shl longitud // 2^longitud

        for (i in 0 until maxNum) {
            val binaryString = Integer.toBinaryString(i).padStart(longitud, '0')
            result.add(binaryString)
        }

        return result
    }
}

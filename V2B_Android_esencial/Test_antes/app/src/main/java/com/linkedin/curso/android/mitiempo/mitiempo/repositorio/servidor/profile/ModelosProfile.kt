package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile

object ModelosProfile {
    data class Resultado(var results: List<Results>, var info: Info)
    data class Results(var gender: String,
                       var name: Name,
                       var email: String,
                       var login: Login,
                       var phone: String,
                       var cell: String,
                       var nat: String,
                       var id: Id,
                       var picture: Picture
    )

    data class Name(var title: String, var first: String, var last: String)
    data class Login(var username: String, var password: String, var salt: String, var md5: String, var sha1: String, var sha256: String)
    data class Id(var name: String, var value: String)
    data class Picture(var large: String, var medium: String, var thumbnail: String)
    data class Info(var seed: String, var results: Int, var page: Int, var version: String)
}
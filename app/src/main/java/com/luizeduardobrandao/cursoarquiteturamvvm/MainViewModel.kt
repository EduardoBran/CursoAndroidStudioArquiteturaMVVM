package com.luizeduardobrandao.cursoarquiteturamvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _login: MutableLiveData<String> = MutableLiveData<String>()
    private val _senha: MutableLiveData<String> = MutableLiveData<String>()

    fun login(): LiveData<String> {
        return _login
    }

    fun senha(): LiveData<String> {
        return _senha
    }

    fun doLogin(email: String, senha: String) {
        var valid = true

        if (email.isBlank()) {
            _login.value = "Informe seu e-mail!"
            valid = false
        }

        if (senha.isBlank()) {
            _senha.value = "Informe sua senha!"
            valid = false
        }

        if (valid) {
            _login.value = "Login efetuado com sucesso!"
            _senha.value = ""  // limpa mensagem de senha, se quiser
        }
    }
}
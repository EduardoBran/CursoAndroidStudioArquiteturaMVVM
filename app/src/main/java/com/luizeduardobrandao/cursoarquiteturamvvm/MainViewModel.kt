package com.luizeduardobrandao.cursoarquiteturamvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _login: MutableLiveData<String> = MutableLiveData<String>()

    fun login(): LiveData<String> {
        return _login
    }

    fun doLogin(name: String) {
        if (name.isEmpty()){
            _login.value = "Informe seu nome!"
        }
        else {
            _login.value = "Login feito com sucesso!"
        }
    }
}
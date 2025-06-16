package com.luizeduardobrandao.cursoarquiteturamvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizeduardobrandao.cursoarquiteturamvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
        setObservers()
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.button_login -> {
                val email = binding.edittextEmail.text.toString()

                // validação simples (se tiver qualquer coisa preenchida em edittext, ativa o botão login)
                viewModel.doLogin(email)
            }
        }
    }

    private fun setObservers() {
        viewModel.login().observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners(){
        binding.buttonLogin.setOnClickListener(this)
    }

}

/*

Conceito Arquitetura MVVM

-> MainActivity não pode lidar com regra de negócio (não pode ser feito "if (email.isNotEmpty()".
    - A MainActivity somente pega e entrega valores ao usuário, só se comunica com interface.
    - A validação de email é delegada a uma ViewModel e essa ViewModel é associada a MainActivity.
    - Uma ViewModel pode ser compartilhada com mais de uma Activity
      (aqui será compartilhada apenas com MainActivity).

-> 1. Criação da ViewModel (é utilizado um padrão chamando o nome da activity "Main" com "ViewModel")

    - Criar o Arquivo MainViewModel
    - Necessita extender a classe ("class MainViewModel: ViewModel()")

-> 2. Criação da função de validação "doLogin" dentro de "MainViewModel"

    - "fun doLogin(name:String)..."

-> 3. Criação do LiveData

    - LiveData é um dado vivo.
    - Definir uma variável do tipo MutableLiveData chamada login

-> 4. Referenciar a ViewModel na MainActivity

    - Criar uma variável do tipo ViewModel para instanciar a classe
        - "private val viewModel: MainViewModel by viewModels()
    - Escutar com "observe()" a variável viewModel em "onClick()" através da função setObserveres()
        - criação da função setObservers()
        - Dentro da função, um dos parâemtros de "observe()" é uma classe anônima, precisa implementar os membros. ("override fun onChanged")
        - trocar o código para lambda (irá ser sugerido pelo AndroidStudio)
    - Dentro do "onCreate()" chamar a função "setObservers()"
    - Dentro de "onClick()" adicionar um ".toString()" a variável "email" e chamar um "viewModel.doLogin(email)".

-> 5. Retornar a "MainViewModel"
    - Em MainActivity ainda podemos editar a variável "login" da ViewModel. Isso não pode acontencer.
    - Criar a função login() para retornar um LiveData da variável login.
    - Alterar para private a variável login.

-> 6. No "MainActivity" pre chamar a função que implementa o met0do de click.

 */
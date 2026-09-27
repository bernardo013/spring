package com.unisinos.crud_grauA.config;

import com.unisinos.crud_grauA.entity.Categoria;
import com.unisinos.crud_grauA.entity.Contato;
import com.unisinos.crud_grauA.entity.Transportadora;
import com.unisinos.crud_grauA.repository.CategoriaRepository;
import com.unisinos.crud_grauA.repository.ContatoRepository;
import com.unisinos.crud_grauA.repository.TransportadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargaInicial implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final TransportadoraRepository transportadoraRepository;
    private final ContatoRepository contatoRepository;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() > 0) {
            return;
        }

        Categoria rodoviario = new Categoria();
        rodoviario.setNome("Rodoviário");
        rodoviario.setDescricao("Transporte de cargas por caminhão");
        categoriaRepository.save(rodoviario);

        Categoria aereo = new Categoria();
        aereo.setNome("Aéreo");
        aereo.setDescricao("Transporte de cargas por avião");
        categoriaRepository.save(aereo);

        Categoria refrigerado = new Categoria();
        refrigerado.setNome("Refrigerado");
        refrigerado.setDescricao("Transporte de cargas com controle de temperatura");
        categoriaRepository.save(refrigerado);

        Transportadora transportadora1 = new Transportadora();
        transportadora1.setRazaoSocial("Transportes Rápido Ltda");
        transportadora1.setCnpj("11111111000101");
        transportadora1.setTelefone("(11) 3000-1111");
        transportadora1.setAtivo(true);
        transportadora1.setCategoria(rodoviario);
        transportadoraRepository.save(transportadora1);

        Transportadora transportadora2 = new Transportadora();
        transportadora2.setRazaoSocial("Cargas do Sul S.A.");
        transportadora2.setCnpj("22222222000102");
        transportadora2.setTelefone("(41) 3000-2222");
        transportadora2.setAtivo(true);
        transportadora2.setCategoria(refrigerado);
        transportadoraRepository.save(transportadora2);

        Transportadora transportadora3 = new Transportadora();
        transportadora3.setRazaoSocial("Expresso Norte Ltda");
        transportadora3.setCnpj("33333333000103");
        transportadora3.setTelefone("(91) 3000-3333");
        transportadora3.setAtivo(false);
        transportadora3.setCategoria(aereo);
        transportadoraRepository.save(transportadora3);

        Contato ana = new Contato();
        ana.setNome("Ana Lima");
        ana.setEmail("ana.lima@transportesrapido.com.br");
        ana.setTelefone("(11) 99001-1111");
        ana.setCargo("Comercial");
        ana.setTransportadora(transportadora1);
        contatoRepository.save(ana);

        Contato bruno = new Contato();
        bruno.setNome("Bruno Ferreira");
        bruno.setEmail("bruno.ferreira@transportesrapido.com.br");
        bruno.setTelefone("(11) 99001-2222");
        bruno.setCargo("Operacional");
        bruno.setTransportadora(transportadora1);
        contatoRepository.save(bruno);

        Contato carla = new Contato();
        carla.setNome("Carla Mendes");
        carla.setEmail("carla.mendes@cargasdosul.com.br");
        carla.setTelefone("(41) 99002-3333");
        carla.setCargo("Financeiro");
        carla.setTransportadora(transportadora2);
        contatoRepository.save(carla);
    }
}

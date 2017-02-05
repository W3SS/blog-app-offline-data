package br.com.thiengo.blogapp.model;


import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.blogapp.presenter.Comentario;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.User;


public class MockData {
    private static int getPosicaoCorreta( int posicao, int max, int min ){
        posicao = posicao >= max ? max - 1 : posicao;
        posicao = posicao < min ? min : posicao;
        return posicao;
    }

    public static User criarUser( int posicao, long id ){
        String[] imagens = {
            "http://www.illumni.co/wp-content/uploads/2014/06/No.-1.jpg",
            "http://kemri-wellcome.org/wp-content/uploads/2016/03/Eunice-Nduati-Black-and-White.jpg",
            "http://kemri-wellcome.org/wp-content/uploads/2016/03/Alun-Davies-Black-and-White-1.jpg",
            "https://cf.dropboxstatic.com/static/images/jobs/jobs_2015/profile-people-ops-liz-vflEQeQ3b.jpg"};
        String[] nomes = {"Wesley Jonathan", "Márcia Champ", "Michale Jackson", "Celina Almeida"};
        User u = new User();

        posicao = getPosicaoCorreta( posicao, imagens.length, 0 );
        u.setId( id );
        u.setUriImagem( imagens[posicao] );
        u.setNome( nomes[posicao] );

        return u;
    }

    public static Comentario criarComentario( int posicao, long id ){
        String[] mensagens = {
            "Neste artigo vamos colocar, passo a passo, notificações bolha em um aplicativo Android, mais precisamente, colocar essas notificações em um aplicativo onde o domínio do problema é um game de Poker.",
            "Notificações bolha ficaram famosas devido ao uso delas no aplicativo de mensagens do Facebook, o Facebook Messenger. Veja abaixo um comparativo do Facebook Messenger com o aplicativo que construiremos neste artigo.",
            "Note que essa técnica utilizada para notificações bolha é totalmente independente do contexto de notificações, digo, a técnica de desenharmos Floating Windows em cima de qualquer outra view, mesmo quando não é a aplicação de origem que está no topo da pilha de atividades.",
            "Aqui vou apresentar o \"desenho de Floating Windows sobre qualquer aplicação\" no contexto de notificação, que como já informado, é onde essa técnica mais ficou conhecida."
        };
        posicao = getPosicaoCorreta( posicao, mensagens.length, 0 );
        User user = criarUser( posicao, id );
        Comentario c = new Comentario();

        c.setId( id );
        c.setUser( user );
        c.setMensagem( mensagens[posicao] );

        return c;
    }

    public static Post criarPost( int posicao, long id, int qtdComentarios ){
        String[] imagens = {
            "http://www.thiengo.com.br/img/post/80-80/mvp-android.png",
            "http://www.thiengo.com.br/img/post/80-80/como-colocar-notificacoes-bolha-em-seu-aplicativo-android.png",
            "http://www.thiengo.com.br/img/post/80-80/top-10-leituras-de-2016-que-sao-boas-pedidas-para-2017.png",
            "http://www.thiengo.com.br/img/post/80-80/androidannotations-entendendo-e-utilizando.png",
            "http://www.thiengo.com.br/img/post/80-80/estudando-android-lista-de-conteudos-do-blog.png",
            "http://www.thiengo.com.br/img/post/80-80/gcmnetworkmanager-para-execucao-de-tarefas-no-background-android.png"
        };
        String[] imagensBanner = {
            "http://www.thiengo.com.br/img/post/facebook/650-366/3calv0a9lf80onidvnmsqmccj1b60ef32453778782be0996f73c4634f2.png",
            "http://www.thiengo.com.br/img/post/facebook/650-366/bvu1h3uqglap0511rgciakf6r79a3b476ad4adbd89218516de0b638c41.png",
            "http://www.thiengo.com.br/img/post/facebook/650-366/v3vhj4smbe3pee4nca1om5pf514e77fcd144763e7b296af09fc97f0f9b.png",
            "http://www.thiengo.com.br/img/post/facebook/650-366/ueuh9n7iaopd1m8qpbflksgla3e61f9e3dd973dd5e0d5fa27fbd2a2742.png",
            "http://www.thiengo.com.br/img/post/facebook/650-366/5l4iafne4fshs8qif2vud3rej42afd8c7163649f81dc1923dd87686217.png",
            "http://www.thiengo.com.br/img/post/facebook/650-366/b6jgs6g3ge8m3vqu65me2ridi4a4d806c03d42fac9a83c7ef47d9862c5.png"
        };
        String[] titulos = {
            "MVP Android",
            "Como Colocar Notificações Bolha em Seu Aplicativo Android",
            "Top 10 leituras de 2016 que são boas pedidas para 2017",
            "AndroidAnnotations, Entendendo e Utilizando",
            "Estudando Android - Lista de Conteúdos do Blog",
            "GCMNetworkManager Para Execução de Tarefas no Background Android"
        };
        String[] sumarios = {
            "Entenda o que é e como utilizar o padrão de arquitetura Model-View-Presenter em aplicativos Android, confira.",
            "Aprenda, passo a passo, como colocar notificações bolha (Floating Windows) em seus aplicativos Android, para melhor apresentar conteúdos não visualizados. Confira.",
            "10 excelentes leituras de 2016, do Blog, que podem fazer parte de sua biblioteca e aumento de produção em 2017, confira.",
            "Melhore a leitura do código de sua APP Android utilizando anotações para construção de scripts padrões que não fazem parte da lógica de negócio, confira.",
            "Estude pela lista, ordenada, de conteúdos em texto e em vídeo, do Blog, para você aprender a construir seus próprios aplicativos Android.",
            "Aprenda a criar um simples aplicativo Android, de GPS tracking, utilizando, para tarefas de background, o GCMNetworkManager."
        };
        String[] conteudos = {
            "Opa, blz? Neste artigo vamos ao estudo e aplicação do padrão de arquitetura Model-View-Presenter (MVP), padrão muito similar ao Model-View-Controller (MVC). Este último que provavelmente você já conhece, principalmente se for também um desenvolvedor Web backend. No artigo, além da teoria para entendimento e importância do MVP, vamos a construção, refatoração, de um projeto de motocicletas Rod Style. Vamos iniciar com o estudo de todo o código e diagramas e logo depois vamos corrigir a arquitetura do projeto aplicando o MVP, pois a inicial estará \"embaralhada\" e ruim para uma evolução limpa do aplicativo.",
            "Opa, blz? Neste artigo vamos colocar, passo a passo, notificações bolha em um aplicativo Android, mais precisamente, colocar essas notificações em um aplicativo onde o domínio do problema é um game de Poker. Notificações bolha ficaram famosas devido ao uso delas no aplicativo de mensagens do Facebook, o Facebook Messenger. Veja abaixo um comparativo do Facebook Messenger com o aplicativo que construiremos neste artigo.",
            "Opa, blz? Nesse artigo vou fazer a versão 2016 do que já venho fazendo em anos anteriores: os top 10 livros que li em 2016. Vou tentar ser o mais breve e objetivo possível nas descrições. Alguns dos livros tenho artigos completos sobre eles aqui no Blog, vou indicar os links nesses casos. Alguns não coloquei um resumo no Blog. Isso, pois adotei uma nova política, mais precisamente a partir da segunda metade do ano, colocar somente conteúdos sobre ou relacionado a programação. O conteúdo desse artigo está dividido em duas principais partes: livros de tecnologia e metodologia; livros de inteligência emocional e vendas. Esse último é um outro conteúdo que tenho como muito importante para aumento de produção de developers (alias, não somente de developers). Antes de continuar a leitura, não deixe de, no final do artigo, dar as suas sugestões de leituras ou opiniões sobre os livros citados aqui.",
            "Opa, blz? Nesse artigo vamos está implementando a library AndroidAnnotations em um projeto Android para que seja possível minimizar a quantidade de código, principalmente aqueles que são padrões e que não fazem parte da lógica de negócio do domínio do problema de nossos aplicativos Android. O AndroidAnnotations é uma library bem conhecida entre desenvolvedores Android (mais de 8 mil estrelas no GitHub), provavelmente devido a facilidade de uso e a redução de código que ela nos ajuda a ter. Antes de prosseguir, não deixe de assinar o blog, logo acima na área de emails, assim receberá o conteúdo em primeira mão e com ainda mais comentários em email.",
            "Opa, blz? Nesse artigo vou listar de maneira ordenada como estudar o conteúdo do Blog e, consequentemente, aprender a programar, criar, aplicatvos Android. Alguns dos seguidores do Blog e do canal no YouTube solicitam, frequentemente, uma lista com a melhor maneira de estudar Android utilizando os conteúdos gratuitos disponíveis aqui. A resposta que enviava, até antes desse artigo, era construída na hora. Algumas vezes alguns conteúdos acabavam sendo indicados para estudo quando na verdade não tinham mais efeitos no dev Android. Outras vezes alguns conteúdos eram esquecidos, conteúdos importantes para entendimento de outros indicados na lista enviada como resposta. Outra critica frequente ao Blog é que tem muito conteúdo depreciado, que não mais está em uso no Android, mas mesmo assim eles persistem nas Play Lists do canal. Os artigos e vídeos sobre a ActionBar, por exemplo. Tendo em mente esses e outros problemas relacionados ao estudo do Android com os conteúdos gratuitos do Blog, foi criado esse artigo com indicações de conteúdos internos ao site, que mesmo quando antigos e listados no texto abaixo no artigo, são importantes tanto quanto (ou mais) os posts mais atuais. É muito importante ressaltar, logo início, que o foco desse artigo é indicar conteúdos do Blog, corretamente ordenados, para um estudo adequado para se tornar um programador Android. Ou seja, os detalhes de cada artigo indicado estarão nos textos e vídeos desses artigos.",
            "Opa, blz? Nesse artigo vamos construir, passo a passo, uma APP de GPS Tracking, isso com o objetivo de mostrar uma maneira eficiente de trabalhar com tarefas de background, utilizando o GCMNetworkManager. No decorrer do conteúdo, além de outros assuntos, também serão abordadas as opções ante ao uso do GCMNetworkManager (AlarmManager, JobScheduler e Sync Adapter), incluindo as vantagens desse, o GCMNetworkManager, sobre estas."
        };
        boolean[] favoritos = { false, true, false, true, false, false };
        Post p = new Post();

        posicao = getPosicaoCorreta( posicao, imagens.length, 0 );
        p.setId( id );
        p.setTitulo( titulos[posicao] );
        p.setUriImagem( imagens[posicao] );
        p.setUriImagemBanner( imagensBanner[posicao] );
        p.setSumario( sumarios[posicao] );
        p.setConteudo( conteudos[posicao] );
        p.setEhFavorito( favoritos[posicao] );

        for( int i = 0; i < qtdComentarios; i++ ){
            posicao = getPosicaoCorreta( i, imagens.length, 0 );
            p.getComentarios().add( criarComentario( posicao, i + 1 ) );
        }

        return p;
    }

    public static List<Post> criarListaPost(){
        List<Post> posts = new ArrayList<>();
        posts.add( criarPost(0, 1, 0) );
        posts.add( criarPost(1, 2, 2) );
        posts.add( criarPost(2, 3, 4) );
        posts.add( criarPost(3, 4, 1) );
        posts.add( criarPost(4, 5, 0) );
        posts.add( criarPost(5, 6, 3) );

        return posts;
    }
}

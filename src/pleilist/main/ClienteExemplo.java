package pleilist.main;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import pleilist.app.PleiList;
import pleilist.app.adapter.Configuration;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.facade.dto.Pair;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.handlers.AdicionarVideoHandler;
import pleilist.app.facade.handlers.CriarPlayListHandler;
import pleilist.app.facade.handlers.PlaylistAleatoriaHandler;
import pleilist.app.facade.handlers.RegistarUtilizadorHandler;
import pleilist.app.facade.handlers.VerPlaylistHandler;

public class ClienteExemplo {
    public static void main(String[] args) {
        
        PleiList p = new PleiList();

        Configuration config = Configuration.getInstance();
        String strategys = config.getString("listStrategy");
        int lastIndexOfIgual = strategys.lastIndexOf('=');
        String linha = strategys.substring(lastIndexOfIgual+1);
        String[] linhaSeparada = linha.split(";");
        String[] estrategias = new String[linhaSeparada.length];
        for(int i = 0; i < linhaSeparada.length; i++) {
            //pleilist.app.strategy. tem 22 caracteres
            estrategias[i] = linhaSeparada[i].substring(22);
            System.out.println("Adicionada a estrategia " + estrategias[i]);
        }

        Scanner myScanner = new Scanner(System.in);

        RegistarUtilizadorHandler regHandler = p.getRegistarUtilizadorHandler();

        regHandler.registarUtilizador("Felismina", "hortadafcul");
        regHandler.registarCurador("Silvino", "bardoc2");
        regHandler.registarCurador("Maribel", "torredotombo");

        Optional<Sessao> talvezSessao = p.autenticar("Silvino", "bardoc2");

        /*
         * ADICIONAR VIDEO 1 SOBRE OOP
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para adicionar video");
                System.out.println("");
                return;
            }
            AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
            avh.iniciarAdicionar();
            avh.definirComoClip(true); // Vamos dizer que eh um clip.
            if(!avh.indicaVideo("OOP in 7 Minutes", "https://www.youtube.com/watch?v=pTB0EiLXUC8")) {
                System.out.println("Esse endereco não existe");
                System.out.println("");
                return;
            }
            avh.indicaDuracao(Duration.ofSeconds(7 * 60 + 33));

            for (String tag : "oop objects polymorphism".split(" ")) {
                avh.indicaTag(tag);
            }

            String codigo = avh.defineComoPublico(true);
            System.out.println("O Silvino criou o video " + codigo);
        });



        /*
         * ADICIONAR VIDEO 2 DE OOP
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para adicionar video");
                System.out.println("");
                return;
            }
            AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
            avh.iniciarAdicionar();
            avh.definirComoClip(true); // Vamos dizer que eh um clip.
            if(!avh.indicaVideo("Object-Oriented Programming", "https://www.youtube.com/watch?v=lbXsrHGhBAU")) {
                System.out.println("Esse endereco não existe");
                System.out.println("");
                return;
            }
            avh.indicaDuracao(Duration.ofSeconds(29 * 60 + 01));

            for (String tag : "oop objects encapsulation".split(" ")) {
                avh.indicaTag(tag);
            }

            String codigo = avh.defineComoPublico(false);
            System.out.println("O Silvino criou o video " + codigo);
        });

        Optional<Sessao> talvezOutraSessao = p.autenticar("Maribel", "torredotombo");

        /*
         * ADICIONAR VIDEO 1 DE NOTICIAS
         */
        talvezOutraSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para adicionar video");
                System.out.println("");
                return;
            }
            AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
            avh.iniciarAdicionar();
            avh.definirComoClip(false); // Vamos dizer que eh um stream.
            if(!avh.indicaVideo("RTP1", "https://www.rtp.pt/play/direto/rtp1")) {
                System.out.println("Esse endereco não existe");
                System.out.println("");
                return;
            }
            //Nao indica duracao! Eh um Stream!

            for (String tag : "portugues actualidade noticias praca_da_alegria".split(" ")) {
                avh.indicaTag(tag);
            }

            String codigo = avh.defineComoPublico(true);
            System.out.println("A Maribel criou o video " + codigo);
        });



        /*
         * ADICIONAR VIDEO 2 DE NOTICIAS
         */
        talvezOutraSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para adicionar video");
                System.out.println("");
                return;
            }
            AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
            avh.iniciarAdicionar();
            avh.definirComoClip(true); 
            if(!avh.indicaVideo("Therapy for Game of Thrones Fans, Uber's Quiet Mode & An Attack on Arnold | The Daily Show", "https://www.youtube.com/watch?v=3I5j_6S94mw")) {
                System.out.println("Esse endereco não existe");
                System.out.println("");
                return;
            }
            avh.indicaDuracao(Duration.ofSeconds(6 * 60 + 18));

            for (String tag : "comedia actualidade noticias satira".split(" ")) {
                avh.indicaTag(tag);
            }

            String codigo = avh.defineComoPublico(true);
            System.out.println("A Maribel criou o video " + codigo);
        });


        /*
         * ADICIONAR VIDEO QUE NAO EXISTE
         */
        talvezOutraSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para adicionar video");
                System.out.println("");
                return;
            }
            AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
            avh.iniciarAdicionar();
            avh.definirComoClip(false); // Vamos dizer que eh um stream.
            if(!avh.indicaVideo("TESTE 404", "https://www.sistrix.com/I-do-not-exist/")) {
                System.out.println("Esse endereco não existe");
                System.out.println("");
                return;
            }

            for (String tag : "teste erros 404 adapters".split(" ")) {
                avh.indicaTag(tag);
            }

            String codigo = avh.defineComoPublico(true);
            System.out.println("A Maribel criou o video " + codigo);
        });



        /*
         * CRIAR PLAYLIST COM VIDEO 1 DE OOP e 1 VIDEO DE PORTUGUES
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para criar playlist manualmente");
                System.out.println("");
                return;
            }
            CriarPlayListHandler cph = p.getCriarPlayListHandler(s);
            cph.iniciaCriacao("Playlist de Domingo");

            for (String tag : "oop portugues".split(" ")) {
                List<Entrada> entradasActuaisDaPlaylist = cph.obterEntradasActuais();
                System.out.println("..........");
                entradasActuaisDaPlaylist.stream().forEach(System.out::println);
                System.out.println("..........");

                List<Pair<String, String>> videos = cph.obterVideosComHashtag(tag);

                String[] respostas = {"S", "N"};
                Random r = new Random();
                String resposta = "";
                if (videos.isEmpty()) {
                    while(!resposta.equals("S") && !resposta.equals("N")) {
                        System.out.println("Quer ver os 10 videos mais visualizados? S ou N?");
                        //meti aleatorio para o stor nao ter de parar e meter no scanner
                        //resposta = myScanner.nextLine();  
                        resposta = respostas[r.nextInt(2)];
                    }
                    if (resposta.equals("S")) {
                        List<Entrada> top10 = cph.mostrarTop10();
                        top10.stream().forEach(System.out::println);
                        System.out.println("");
                        break;
                    } 
                } else {
                    String codigo = videos.get(0).getSecond();
                    Pair<Boolean, Boolean> par= cph.indicarCodigo(codigo);
                    boolean eStream = par.getFirst();
                    boolean existe = par.getSecond();
                    
                    if(!existe) {
                        System.out.println("Este video nao existe");
                        System.out.println("");
                        return;
                    }

                    if (eStream) {
                        cph.indicaDuracao(Duration.ofMinutes(30));
                    }
                }
            }
            System.out.println("Playlist de Domingo final");
            cph.obterEntradasActuais().stream().forEach(System.out::println);
            System.out.println("");
        });

        /*
         * CRIAR PLAYLIST COM VIDEO QUE NAO EXISTE
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para criar playlist manualmente");
                System.out.println("");
                return;
            }
            CriarPlayListHandler cph = p.getCriarPlayListHandler(s);
            cph.iniciaCriacao("Playlist de Domingo_2");

            for (String tag : "oop portugues".split(" ")) {
                List<Entrada> entradasActuaisDaPlaylist = cph.obterEntradasActuais();
                System.out.println("..........");
                entradasActuaisDaPlaylist.stream().forEach(System.out::println);
                System.out.println("..........");

                List<Pair<String, String>> videos = cph.obterVideosComHashtag(tag);

                String[] respostas = {"S", "N"};
                Random r = new Random();
                String resposta = "";
                if (videos.isEmpty()) {
                    while(!resposta.equals("S") && !resposta.equals("N")) {
                        System.out.println("Quer ver os 10 videos mais visualizados? S ou N?");
                        //meti aleatorio para o stor nao ter de parar e meter no scanner
                        //resposta = myScanner.nextLine();  
                        resposta = respostas[r.nextInt(2)];
                    }
                    if (resposta.equals("S")) {
                        List<Entrada> top10 = cph.mostrarTop10();
                        top10.stream().forEach(System.out::println);
                        System.out.println("");
                        break;
                    } 
                } else {
                    String codigo = "ESTE CODIGO NAO EXISTE";
                    Pair<Boolean, Boolean> par= cph.indicarCodigo(codigo);
                    boolean eStream = par.getFirst();
                    boolean existe = par.getSecond();
                    
                    if(!existe) {
                        System.out.println("Este video nao existe");
                        System.out.println("");
                        return;
                    }

                    if (eStream) {
                        cph.indicaDuracao(Duration.ofMinutes(30));
                    }
                }
            }
            System.out.println("Playlist de Domingo final_2");
            cph.obterEntradasActuais().stream().forEach(System.out::println);
            System.out.println("");
        });
        
        
        
        /**
         * CRIAR PLAYLIST COM TAGS QUE NAO EXISTEM
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para criar playlist manualmente");
                System.out.println("");
                return;
            }
            CriarPlayListHandler cph = p.getCriarPlayListHandler(s);
            cph.iniciaCriacao("Playlist de Jogos Multijogador");

            for (String tag : "fortnite jogos streamers".split(" ")) {
                List<Entrada> entradasActuaisDaPlaylist = cph.obterEntradasActuais();
                System.out.println("..........");
                entradasActuaisDaPlaylist.stream().forEach(System.out::println);
                System.out.println("..........");

                List<Pair<String, String>> videos = cph.obterVideosComHashtag(tag);

                String[] respostas = {"S", "N"};
                Random r = new Random();
                String resposta = "";
                if (videos.isEmpty()) {
                    while(!resposta.equals("S") && !resposta.equals("N")) {
                        System.out.println("Quer ver os 10 videos mais visualizados? S ou N?");
                        //meti aleatorio so para o stor nao ter de parar para escolher
                        //resposta = myScanner.nextLine();  
                        resposta = respostas[r.nextInt(2)];
                    }
                    if (resposta.equals("S")) {
                        List<Entrada> top10 = cph.mostrarTop10();
                        top10.stream().forEach(System.out::println);
                        System.out.println("");
                        break;
                    } 
                } else {
                    String codigo = videos.get(0).getSecond();
                    Pair<Boolean, Boolean> par= cph.indicarCodigo(codigo);
                    boolean eStream = par.getFirst();
                    boolean existe = par.getSecond();
                    
                    if(!existe) {
                        System.out.println("Este video nao existe");
                        System.out.println("");
                        return;
                    }

                    if (eStream) {
                        cph.indicaDuracao(Duration.ofMinutes(30));
                    }
                }
            }
            System.out.println("Playlist de Jogos Multijogador final");
            cph.obterEntradasActuais().stream().forEach(System.out::println);
            System.out.println("");
        });



        Optional<Sessao> talvezSessaoUtilizador = p.autenticar("Felismina", "hortadafcul");
        
        /*
         * TESTAR UTILIZADOR REALIZAR FUNCAO QUE NAO DEVIA
         */
        talvezSessaoUtilizador.ifPresent( (Sessao s) -> {
            if(!(s.ehCurador())) {
                System.out.println("Este utilizador nao tem permissao para adicionar video");
                System.out.println("");
                return;
            }
            //estah a tentar adicionar um video que jah existe, mas pronto so para dar o return
            AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
            avh.iniciarAdicionar();
            avh.definirComoClip(false); // Vamos dizer que eh um stream.
            if(!avh.indicaVideo("TESTE 404", "https://www.sistrix.com/I-do-not-exist/")) {
                System.out.println("Esse endereco não existe");
                return;
            }
            for (String tag : "teste erros 404 adapters".split(" ")) {
                avh.indicaTag(tag);
            }

            String codigo = avh.defineComoPublico(true);
            System.out.println("A Maribel criou o video " + codigo);
        });
        
        /*
         * FAZER PLAYLIST DE 2 VIDEOS COM RANDOMVIDEOS AQUI
         */
        talvezSessaoUtilizador.ifPresent( (Sessao s) -> {

            PlaylistAleatoriaHandler pah = p.getPlaylistAleatoriaHandler(s);
            pah.comecarPlaylist("Playlist Random Videos");
            pah.decidirEstrategia(estrategias[1]);
            String codigo = pah.obterPlaylist (10);
            System.out.println("Playlist Random Videos final");
            pah.listaEntradas().stream().forEach(System.out::println);
            System.out.println("");
        });



        /**
         * FAZER PLAYLIST DE 4 VIDEOS COM RANDOMCHAINEDVIDEOS AQUI
         */
        talvezSessaoUtilizador.ifPresent( (Sessao s) -> {

            PlaylistAleatoriaHandler pah = p.getPlaylistAleatoriaHandler(s);
            pah.comecarPlaylist("Playlist Chained Videos");
            pah.decidirEstrategia(estrategias[0]);
            String codigo = pah.obterPlaylist (10);
            System.out.println("Playlist Chained Videos final");
            pah.listaEntradas().stream().forEach(System.out::println);
            System.out.println("");
        });



        /**
         * VER E CLASSIFICAR OS VIDEOS DA PLAYLIST DE DOMINGO
         */
        talvezSessaoUtilizador.ifPresent( (Sessao s) -> {
            VerPlaylistHandler vph = p.getVerPlaylistHandler(s);
            List<Playlist> resultados = vph.procurarPorTag("oop");
            String codigo = "";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getNome().equals("Playlist de Domingo")) {
                    codigo = resultados.get(i).getCodigo();
                    break;
                }
            }
            System.out.println(s.getUtilizador().getUsername());
            System.out.println("/*** A ver a playlist: Playlist de Domingo ***/");
            Duration duracaoTotal = vph.escolhePlaylist(codigo);
            Duration percorrida = Duration.ofSeconds(0);
            while(percorrida.compareTo(duracaoTotal) < 0) {
                Pair<String, Duration>	video = vph.verProximo();
                System.out.println("A ver o video: " + video.getFirst());
                Random r = new Random();
                int rating = r.nextInt(6);
                System.out.println("Dei-lhe o rating " + rating + " estrelas");
                vph.classificar(rating);
                percorrida = percorrida.plus(video.getSecond());
            }
            System.out.println("/*******************************************************/");
            System.out.println("");     

            //imprimir a classificacao final
            // procurar por tag outra vez para criar nova facade
            List<Playlist> resultadosFinais = vph.procurarPorTag("oop");
            Playlist resultadoFinal = null;
            for (int i = 0; i < resultadosFinais.size(); i++) {
                if (resultadosFinais.get(i).getNome().equals("Playlist de Domingo")) {
                    resultadoFinal = resultadosFinais.get(i);
                    break;
                }
            }
            System.out.println("A lista de nome: Playlist de Domingo acabou com a pontuacao " + resultadoFinal.getPontuacao());
            System.out.println("");     
        });



        /**
         * VER E CLASSIFICAR OS VIDEOS DA PLAYLIST DE RANDOM VIDEOS
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            VerPlaylistHandler vph = p.getVerPlaylistHandler(s);
            List<Playlist> resultados = vph.procurarPorTag("oop");
            String codigo = "";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getNome().equals("Playlist Random Videos")) {
                    codigo = resultados.get(i).getCodigo();
                    break;
                }
            }
            System.out.println(s.getUtilizador().getUsername());
            System.out.println("/*** A ver a playlist: Playlist Random Videos ***/");
            Duration duracaoTotal = vph.escolhePlaylist(codigo);
            Duration percorrida = Duration.ofSeconds(0);
            while(percorrida.compareTo(duracaoTotal) < 0) {
                Pair<String, Duration>  video = vph.verProximo();
                System.out.println("A ver o video: " + video.getFirst());
                Random r = new Random();
                int rating = r.nextInt(6);
                System.out.println("Dei-lhe o rating " + rating + " estrelas");
                vph.classificar(rating);
                percorrida = percorrida.plus(video.getSecond());
            }
            System.out.println("/*******************************************************/");
            System.out.println("");     

            //imprimir a classificacao final
            // procurar por tag outra vez para criar nova facade
            List<Playlist> resultadosFinais = vph.procurarPorTag("oop");
            Playlist resultadoFinal = null;
            for (int i = 0; i < resultadosFinais.size(); i++) {
                if (resultadosFinais.get(i).getNome().equals("Playlist Random Videos")) {
                    resultadoFinal = resultadosFinais.get(i);
                    break;
                }
            }
            System.out.println("A lista de nome: Playlist Random Videos acabou com a pontuacao " + resultadoFinal.getPontuacao());
            System.out.println("");     
        });


        /*
         * VER E CLASSIFICAR OS VIDEOS DA PLAYLIST DE CHAINED VIDEOS
         * PROCURAR COM ACTUALIDADE OOP
         */
        talvezOutraSessao.ifPresent( (Sessao s) -> {
            VerPlaylistHandler vph = p.getVerPlaylistHandler(s);
            List<Playlist> resultados = vph.procurarPorTag("oop");
            String codigo = "";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getNome().equals("Playlist Chained Videos")) {
                    codigo = resultados.get(i).getCodigo();
                    break;
                }
            } 
            if (codigo.equals("")) {
                System.out.println("A Playlist Chained Videos nao tem o hashtag oop");
                System.out.println("");     
                return;
            }
            System.out.println(s.getUtilizador().getUsername());
            System.out.println("/*** A ver a playlist: Playlist Chained Videos ***/");
            Duration duracaoTotal = vph.escolhePlaylist(codigo);
            Duration percorrida = Duration.ofSeconds(0);
            while(percorrida.compareTo(duracaoTotal) < 0) {
                Pair<String, Duration>  video = vph.verProximo();
                System.out.println("A ver o video: " + video.getFirst());
                Random r = new Random();
                int rating = r.nextInt(6);
                System.out.println("Dei-lhe o rating " + rating + " estrelas");
                vph.classificar(rating);
                percorrida = percorrida.plus(video.getSecond());
            }
            System.out.println("/*******************************************************/");
            System.out.println("");     

            //imprimir a classificacao final
            // procurar por tag outra vez para criar nova facade
            List<Playlist> resultadosFinais = vph.procurarPorTag("oop");
            Playlist resultadoFinal = null;
            for (int i = 0; i < resultadosFinais.size(); i++) {
                if (resultadosFinais.get(i).getNome().equals("Playlist Chained Videos")) {
                    resultadoFinal = resultadosFinais.get(i);
                    break;
                }
            }
            System.out.println("A lista de nome: Playlist Chained Videos acabou com a pontuacao " + resultadoFinal.getPontuacao());
            System.out.println("");     
        });

        /**
         * VER E CLASSIFICAR OS VIDEOS DA PLAYLIST DE CHAINED VIDEOS
         * PROCURAR COM HASHTAG ACTUALIDADE
         */
        talvezOutraSessao.ifPresent( (Sessao s) -> {
            VerPlaylistHandler vph = p.getVerPlaylistHandler(s);
            List<Playlist> resultados = vph.procurarPorTag("actualidade");
            String codigo = "";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getNome().equals("Playlist Chained Videos")) {
                    codigo = resultados.get(i).getCodigo();
                    break;
                }
            } 
            if (codigo.equals("")) {
                System.out.println("A Playlist Chained Videos nao tem o hashtag actualidade");
                System.out.println("");     
                return;
            }
            System.out.println(s.getUtilizador().getUsername());
            System.out.println("/*** A ver a playlist: Playlist Chained Videos ***/");
            Duration duracaoTotal = vph.escolhePlaylist(codigo);
            Duration percorrida = Duration.ofSeconds(0);
            while(percorrida.compareTo(duracaoTotal) < 0) {
                Pair<String, Duration>  video = vph.verProximo();
                System.out.println("A ver o video: " + video.getFirst());
                Random r = new Random();
                int rating = r.nextInt(6);
                System.out.println("Dei-lhe o rating " + rating + " estrelas");
                vph.classificar(rating);
                percorrida = percorrida.plus(video.getSecond());
            }
            System.out.println("/*******************************************************/");
            System.out.println("");     

            //imprimir a classificacao final
            // procurar por tag outra vez para criar nova facade
            List<Playlist> resultadosFinais = vph.procurarPorTag("actualidade");
            Playlist resultadoFinal = null;
            for (int i = 0; i < resultadosFinais.size(); i++) {
                if (resultadosFinais.get(i).getNome().equals("Playlist Chained Videos")) {
                    resultadoFinal = resultadosFinais.get(i);
                    break;
                }
            }
            System.out.println("A lista de nome: Playlist Chained Videos acabou com a pontuacao " + resultadoFinal.getPontuacao());
            System.out.println("");     
        });

        /**
         * FAZER PLAYLIST DE 2 VIDEOS COM TOPRANKED AQUI
         */
        talvezSessaoUtilizador.ifPresent( (Sessao s) -> {

            PlaylistAleatoriaHandler pah = p.getPlaylistAleatoriaHandler(s);
            pah.comecarPlaylist("Playlist Top Videos");
            pah.decidirEstrategia(estrategias[2]);
            String codigo = pah.obterPlaylist (2);
            System.out.println("Playlist Top Videos final");
            pah.listaEntradas().stream().forEach(System.out::println);
            System.out.println("");
        });

        /**
         * VER E CLASSIFICAR OS VIDEOS DA PLAYLIST DE TOP VIDEOS
         * PROCURAR POR HASHTAG OOP
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            VerPlaylistHandler vph = p.getVerPlaylistHandler(s);

            List<Playlist> resultados = vph.procurarPorTag("oop");
            String codigo = "";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getNome().equals("Playlist Top Videos")) {
                    codigo = resultados.get(i).getCodigo();
                    break;
                }
            }
            if (codigo.equals("")) {
                System.out.println("A Playlist Top Videos nao tem o hashtag oop");
                System.out.println("");     
                return;
            }
            System.out.println(s.getUtilizador().getUsername());
            System.out.println("/*** A ver a playlist: Playlist Top Videos ***/");
            Duration duracaoTotal = vph.escolhePlaylist(codigo);
            Duration percorrida = Duration.ofSeconds(0);
            while(percorrida.compareTo(duracaoTotal) < 0) {
                Pair<String, Duration>  video = vph.verProximo();
                System.out.println("A ver o video: " + video.getFirst());
                Random r = new Random();
                int rating = r.nextInt(6);
                System.out.println("Dei-lhe o rating " + rating + " estrelas");
                vph.classificar(rating);
                percorrida = percorrida.plus(video.getSecond());
            }
            System.out.println("/*******************************************************/");
            System.out.println("");     

            //imprimir a classificacao final
            // procurar por tag outra vez para criar nova facade
            List<Playlist> resultadosFinais = vph.procurarPorTag("oop");
            Playlist resultadoFinal = null;
            for (int i = 0; i < resultadosFinais.size(); i++) {
                if (resultadosFinais.get(i).getNome().equals("Playlist Top Videos")) {
                    resultadoFinal = resultadosFinais.get(i);
                    break;
                }
            }
            System.out.println("A lista de nome: Playlist Top Videos acabou com a pontuacao " + resultadoFinal.getPontuacao());
            System.out.println("");     
        });

        /**
         * VER E CLASSIFICAR OS VIDEOS DA PLAYLIST DE TOP VIDEOS
         * PROCURAR POR HASHTAG OOP
         */
        talvezSessao.ifPresent( (Sessao s) -> {
            VerPlaylistHandler vph = p.getVerPlaylistHandler(s);

            List<Playlist> resultados = vph.procurarPorTag("actualidade");
            String codigo = "";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).getNome().equals("Playlist Top Videos")) {
                    codigo = resultados.get(i).getCodigo();
                    break;
                }
            }
            if (codigo.equals("")) {
                System.out.println("A Playlist Top Videos nao tem o hashtag actualidade");
                System.out.println("");     
                return;
            }
            System.out.println(s.getUtilizador().getUsername());
            System.out.println("/*** A ver a playlist: Playlist Top Videos ***/");
            Duration duracaoTotal = vph.escolhePlaylist(codigo);
            Duration percorrida = Duration.ofSeconds(0);
            while(percorrida.compareTo(duracaoTotal) < 0) {
                Pair<String, Duration>  video = vph.verProximo();
                System.out.println("A ver o video: " + video.getFirst());
                Random r = new Random();
                int rating = r.nextInt(6);
                System.out.println("Dei-lhe o rating " + rating + " estrelas");
                vph.classificar(rating);
                percorrida = percorrida.plus(video.getSecond());
            }
            System.out.println("/*******************************************************/");
            System.out.println("");     

            //imprimir a classificacao final
            // procurar por tag outra vez para criar nova facade
            List<Playlist> resultadosFinais = vph.procurarPorTag("actualidade");
            Playlist resultadoFinal = null;
            for (int i = 0; i < resultadosFinais.size(); i++) {
                if (resultadosFinais.get(i).getNome().equals("Playlist Top Videos")) {
                    resultadoFinal = resultadosFinais.get(i);
                    break;
                }
            }
            System.out.println("A lista de nome: Playlist Top Videos acabou com a pontuacao " + resultadoFinal.getPontuacao());
            System.out.println("");     
        });

        myScanner.close();
    }
}

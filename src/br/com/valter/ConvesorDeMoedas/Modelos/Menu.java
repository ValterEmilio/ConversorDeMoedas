package br.com.valter.ConvesorDeMoedas.Modelos;

public class Menu {
    public void mostrarMenu(){
        System.out.println("""
                =================================================================
                1 - Dolar(USD) =>> Peso Argentino(ARS);
                2 - Peso Argentino(ARS) =>> Dolar(USD);
                3 - Boliviano da Bolivia(BOB) =>> Real Brasileiro(BRL);
                4 - Real Brasileiro(BRL) =>> Boliviano da Bolivia(BOB);
                5 - Peso Chileno(CLP) =>> Peso Colombiano(COP);
                6 - Peso Colombiano(COP) =>> Peso Chileno(CLP);
                7 - Consultar Histórico de Convesões;
                8 - Sair ();
                ------------------------------------------------------------------
                Escolha a opção para qual moeda Deseja Converter:""");
    }
}

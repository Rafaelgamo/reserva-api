package api.reservas.api.enums;

public enum NotaAvaliacao {
        UM(1, "Ruim"),
        DOIS(2, "Regular"),
        TRES(3, "Bom"),
        QUATRO(4, "Muito Bom"),
        CINCO(5, "Excelente");

        private final int valor;
        private final String descricao;


        NotaAvaliacao(int valor, String descricao) {
            this.valor = valor;
            this.descricao = descricao;
        }

        public int getValor() {
            return valor;
        }

        public String getDescricao() {
            return descricao;
        }

        public static NotaAvaliacao forId(int i) {
            for (NotaAvaliacao nota : NotaAvaliacao.values()) {
                if (nota.getValor() == i) {
                    return nota;
                }
            }
            throw new IllegalArgumentException("Nota inválida: " + i);
        }
    }

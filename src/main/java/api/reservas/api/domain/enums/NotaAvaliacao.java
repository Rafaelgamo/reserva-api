package api.reservas.api.domain.enums;

public enum NotaAvaliacao {
        UM(1, "Muito Ruim"),
        DOIS(2, "Ruim"),
        TRES(3, "Regular"),
        QUATRO(4, "Ótimo"),
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

        public static NotaAvaliacao of(int valor) {
            for (NotaAvaliacao nota : NotaAvaliacao.values()) {
                if (nota.getValor() == valor) {
                    return nota;
                }
            }
            throw new IllegalArgumentException("Nota inválida: " + valor);
        }
    }

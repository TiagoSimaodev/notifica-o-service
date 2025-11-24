package br.com.pedidos.notificacao.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.pedidos.notificacao.entity.Pedido;
import br.com.pedidos.notificacao.service.EmailService;

@Component
public class PedidoListener {

	
	
	private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);
	
	private final EmailService emailService;
	
	public PedidoListener(EmailService emailService ) {
		this.emailService = emailService;
	}
	
	
	@RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-notificacao" )
	public void enviarNotificacao(Pedido pedido) {
		emailService.enviarEmail(pedido);
		logger.info("Notificação gerada: {}", pedido.toString());
	}
	
}

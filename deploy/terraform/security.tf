resource "aws_security_group" "pedidos_sg" {
  name        = "pedidos_sg"
  description = "pedidos security group"
  vpc_id      = aws_vpc.pedidos_vpc.id
  tags        = {
    "Name" = "pedidos_sg"
  }
}

resource "aws_security_group_rule" "public_out" {
  type              = "egress"
  from_port         = 0
  to_port           = 0
  protocol          = "-1"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.pedidos_sg.id
}

resource "aws_security_group_rule" "public_in_ssh" {
  type              = "ingress"
  from_port         = 22
  to_port           = 22
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.pedidos_sg.id
}

resource "aws_key_pair" "pedidos-api-key" {
  key_name   = "wesley-api-key"
  public_key = file("~/.ssh/wesley-api-key.pub")
}

resource "aws_security_group_rule" "public_in_http" {
  type              = "ingress"
  from_port         = 80
  to_port           = 80
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.pedidos_sg.id
}
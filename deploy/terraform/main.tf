resource "aws_vpc" "pedidos_vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    "Name" = "pedidos_vpc"
  }
}

resource "aws_subnet" "pedidos_subnet_pub_1a" {
  vpc_id                  = aws_vpc.pedidos_vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    "Name" = "pedidos_subnet_pub_1a"
  }
}

resource "aws_internet_gateway" "pedidos_igw_1a" {
  vpc_id = aws_vpc.pedidos_vpc.id

  tags = {
    "Name" = "pedidos_igw_1a"
  }
}

resource "aws_route_table" "pedidos_rtb_pub" {
  vpc_id = aws_vpc.pedidos_vpc.id

  tags = {
    "Name" = "pedidos_rtb_pub"
  }
}

resource "aws_route" "pedidos_default_rtb" {
  route_table_id         = aws_route_table.pedidos_rtb_pub.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.pedidos_igw_1a.id
}

resource "aws_route_table_association" "pedidos_rtba_pub_1a" {
  route_table_id = aws_route_table.pedidos_rtb_pub.id
  subnet_id      = aws_subnet.pedidos_subnet_pub_1a.id
}

resource "aws_instance" "pedidos_ec2_inst" {
  instance_type          = "t2.micro"
  ami                    = data.aws_ami.pedidos_server_ami.id
  key_name               = aws_key_pair.pedidos-api-key.id
  vpc_security_group_ids = [aws_security_group.pedidos_sg.id]
  subnet_id              = aws_subnet.pedidos_subnet_pub_1a.id
  user_data              = file("userdata.tpl")
  root_block_device {
    volume_size = 8
  }

  tags = {
    "Name" = "pedidos_ec2_inst"
  }
}


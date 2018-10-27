<?PHP
    $hostname = "localhost";
    $database = "id7595289_grupo_9_castuera_db";
    $username = "id7595289_siloscordoba";
    $password = "P@ssw0rd";
    $json = array();

    if(isset($_GET["user"]) && isset($_GET["pwd"]))
    {
        $usuario = $_GET['user'];
        $clave = $_GET['pwd'];
        $conexion = mysqli_connect($hostname, $username, $password, $database);

        $consulta = "SELECT CONCAT(nombre_usuario, ' ' , apellidos_usuario) AS nombres, tipo_usuario FROM usuario WHERE nif_usuario = '{$usuario}' AND password_usuario = '{$clave}'";
        $resultado = mysqli_query($conexion, $consulta);

        if($consulta)
        {
            if ($registro = mysqli_fetch_array($resultado)) 
            {
                $json['datos'][] = $registro;
            }

            mysqli_close($conexion);
            echo json_encode($json);
        }
        else
        {
            $results["nombres"] = '';
			$results["tipo"] = '';
			$json['datos'][] = $results;
			echo json_encode($json);
        }
    }
    else
    {
        $results["nombres"] = '';
		$results["tipo"] = '';
		$json['datos'][] = $results;
		echo json_encode($json);
    }
?>
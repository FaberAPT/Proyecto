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

        $consulta = "SELECT a.cif_empresa, a.nombre_empresa, a.direccion_empresa, a.codigo_postal, a.provincia, ".
                            "CONCAT(b.nombre_usuario, ' ' , b.apellidos_usuario) AS nombres, b.tipo_usuario ".
                    "FROM informacion_empresa a INNER JOIN usuario b ON (a.cif_empresa = b.cif_empresa) ".
                    "WHERE b.nif_usuario = '{$usuario}' AND b.password_usuario = '{$clave}'";
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
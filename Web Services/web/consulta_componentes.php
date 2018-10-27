<?PHP
    $hostname = "localhost";
    $database = "id7595289_grupo_9_castuera_db";
    $username = "id7595289_siloscordoba";
    $password = "P@ssw0rd";

    $json = array();

    $conexion = mysqli_connect($hostname, $username, $password, $database);

    $consulta = "SELECT * FROM componente ORDER BY nombre_componente";
    $resultado = mysqli_query($conexion, $consulta);

    while($registro = mysqli_fetch_array($resultado))
    {
        $json['componente'][] = $registro;
    }

    mysqli_close($conexion);
    echo json_encode($json);
?>
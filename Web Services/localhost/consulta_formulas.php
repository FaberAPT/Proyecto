<?PHP
    $hostname = "localhost";
    $database = "grupo_9_castuera_db";
    $username = "root";
    $password = "";

    $json = array();

    $conexion = mysqli_connect($hostname, $username, $password, $database);

    $consulta = "SELECT * FROM formula ORDER BY nombre_formula, id_formula";
    $resultado = mysqli_query($conexion, $consulta);

    while($registro = mysqli_fetch_array($resultado))
    {
        $json['formula'][] = $registro;
    }

    mysqli_close($conexion);
    echo json_encode($json);
?>
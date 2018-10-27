<?PHP
    $hostname = "localhost";
    $database = "grupo_9_castuera_db";
    $username = "root";
    $password = "";

    $json = array();

    $conexion = mysqli_connect($hostname, $username, $password, $database);

    $consulta = "SELECT id_formula, nombre_formula, fecha_formula, fecha_modificacion, ".
                        "CASE tipo_formula WHEN 1 THEN 'Normal' WHEN 2 THEN 'Limpieza' WHEN 3 THEN 'Medicada' END AS tipo_formula, ".
                        "CASE estado_formula WHEN 0 THEN 'Inactiva' WHEN 1 THEN 'Activa' END AS estado_formula ". 
                        "FROM formula ORDER BY nombre_formula, id_formula";
    $resultado = mysqli_query($conexion, $consulta);

    while($registro = mysqli_fetch_array($resultado))
    {
        $json['formula'][] = $registro;
    }

    mysqli_close($conexion);
    echo json_encode($json);
?>